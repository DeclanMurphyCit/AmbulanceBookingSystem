package com.abs.controller;

import com.abs.domain.*;
import com.abs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
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
        model.addAttribute("crewuser", new CrewUser());
        return "addNewCrew";
    }

    @RequestMapping(value = "/addNewCrew", method = RequestMethod.POST)
    public String addNewCrew(@ModelAttribute("crewuser")  CrewUser crewUser,
                             BindingResult result, ModelMap model) {

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
