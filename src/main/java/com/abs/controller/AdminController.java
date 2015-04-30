package com.abs.controller;

import com.abs.domain.*;
import com.abs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Declan on 10/04/2015.
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AmbulanceBookingDAO ambulanceBookingDAO;
    @Autowired
    AmbulanceCrewDAO ambulanceCrewDAO;
    @Autowired
    AmbulanceCompanyDAO ambulanceCompanyDAO;
    @Autowired
    PatientDAO patientDAO;
    @Autowired
    UserObjDAO userObjDAO;
    @Autowired
    LocationDAO locationDAO;
    @Autowired
    private ServletContext servletContext;


    @RequestMapping(value = "/addNewCrew", method = RequestMethod.GET)
    public String addNewCompany(ModelMap model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.getName().equals("anonymousUser"))
        {
            model.addAttribute("error","You must be logged in to make a booking request!");
            return "login";
        }
        String name = auth.getName();
        UserObj userLoggedIn = userObjDAO.getUserByUsername(name);//This will be the ambulance company user
        AmbulanceCompany ac = ambulanceCompanyDAO.getCompanyUserId(userLoggedIn.getId());

        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        boolean isAdon = authorities.contains(new SimpleGrantedAuthority("ROLE_ADON"));
        boolean isAmbComp = authorities.contains(new SimpleGrantedAuthority("ROLE_AMB_COMP"));

        String alert = "";

        if(isAdon==true)
        {//The following is used to check if the user is an ADON and if they require a notification
            List<AmbulanceBooking> listBookings = ambulanceBookingDAO.getAllUnapprovedBookings();
            if(listBookings.size() > 0)
                alert = "adon";

        } else if(isAmbComp) {
            //The following is used to check if the user is an amb company and if they require a notification
            UserObj userObj = userObjDAO.getUserByUsername(name);
            AmbulanceCompany ambCompany = ambulanceCompanyDAO.getCompanyUserId(userObj.getId());
            List<AmbulanceBooking> listBookings = ambulanceBookingDAO.getUnassignedBookingsForAmbCompany(ambCompany.getId());
            if(listBookings.size() > 0)
                alert = "ambCompany";

        }

        model.addAttribute("alert",alert);

        model.addAttribute("title","Add New Crew");

        model.addAttribute("crewuser", new CrewUser());
        return "addNewCrew";
    }

    @RequestMapping(value = "/addNewCrew", method = RequestMethod.POST)
    public String addNewCrew(@ModelAttribute("crewuser")  CrewUser crewUser,
                             BindingResult result, ModelMap model) {

        model.addAttribute("title","Add Crew");

        Integer createdUserId;

        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if(auth.getName().equals("anonymousUser"))
            {
                model.addAttribute("error","You must be logged in to make a booking request!");
                return "login";
            }
            String name = auth.getName();
            UserObj userLoggedIn = userObjDAO.getUserByUsername(name);//This will be the ambulance company user
            AmbulanceCompany ac = ambulanceCompanyDAO.getCompanyUserId(userLoggedIn.getId());

            Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
            boolean isAdon = authorities.contains(new SimpleGrantedAuthority("ROLE_ADON"));
            boolean isAmbComp = authorities.contains(new SimpleGrantedAuthority("ROLE_AMB_COMP"));

            String alert = "";

            if(isAdon==true)
            {//The following is used to check if the user is an ADON and if they require a notification
                List<AmbulanceBooking> listBookings = ambulanceBookingDAO.getAllUnapprovedBookings();
                if(listBookings.size() > 0)
                    alert = "adon";

            } else if(isAmbComp) {
                //The following is used to check if the user is an amb company and if they require a notification
                UserObj userObj = userObjDAO.getUserByUsername(name);
                AmbulanceCompany ambCompany = ambulanceCompanyDAO.getCompanyUserId(userObj.getId());
                List<AmbulanceBooking> listBookings = ambulanceBookingDAO.getUnassignedBookingsForAmbCompany(ambCompany.getId());
                if(listBookings.size() > 0)
                    alert = "ambCompany";

            }

            model.addAttribute("alert",alert);

            String username = crewUser.getUserObj().getUserName();
            String password = crewUser.getUserObj().getPassword();
            String crewIdentifier = crewUser.getAmbulanceCrew().getcrewIdentifier();

            createdUserId =  userObjDAO.createUserGetId(username,password,username,crewIdentifier,"ROLE_AMB_CREW");
            ambulanceCrewDAO.createAmbulanceCrewGetId(ac.getId(),createdUserId,crewIdentifier);

            model.addAttribute("id", createdUserId);

        } catch (Exception e) {
            System.out.println("ERROR2 ");
            e.printStackTrace();
            model.addAttribute("message", "Creation of user failed, " + e.getLocalizedMessage());
            return "error";

        }

        return "home";
    }
}
