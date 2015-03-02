package com.abs.controller;

import com.abs.domain.*;
import com.abs.service.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Declan on 23/02/2015.
 */

@Controller
@RequestMapping("/ambcompany")
public class AmbCompanyController {

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

    @RequestMapping(value={"/bookingStandby"}, method = RequestMethod.GET)
    public String bookingPermission(ModelMap model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.getName().equals("anonymousUser"))
        {
            model.addAttribute("error","You must be logged in to view the standby page!");
            return "login";
        }
        String name = auth.getName();
        UserObj userObj = userObjDAO.getUserByUsername(name);

        AmbulanceCompany ambCompany = ambulanceCompanyDAO.getCompanyUserId(userObj.getId());

        List<AmbulanceBooking> listBookings = ambulanceBookingDAO.getNewBookingsForAmbCompany(ambCompany.getId());
        model.addAttribute("bookings", listBookings);
        List<AmbulanceCrew> listCrews = ambulanceCrewDAO.getAllCrews();

        String bookingIdArray = " ";

        Map<Integer,String> listPatients = new HashMap<Integer,String>();

        for(AmbulanceBooking b : listBookings)
        {
            bookingIdArray += b.getBookingId() + ", ";
            Patient p = patientDAO.getPatient(b.getPatientId());
            String patientName = p.getFirstName() + " " + p.getLastName();
            listPatients.put(b.getBookingId(),patientName );
        }

        bookingIdArray = bookingIdArray.substring(0, bookingIdArray.length()-1);
        model.addAttribute("bookingIdArray",bookingIdArray);
        model.addAttribute("patients", listPatients);
        List<Location> listLoc = locationDAO.getAllLocations();
        model.addAttribute("locations", listLoc);
        model.addAttribute("numberOfBookings", listBookings.size());

        return "bookingStandby";
    }

    @RequestMapping(value={"/cancelBooking"}, method = RequestMethod.POST   )
    public @ResponseBody String denyBooking(@ModelAttribute("bookingId") String bookingId, BindingResult result) {
        Integer bid = Integer.parseInt(bookingId);
        ambulanceBookingDAO.setAmbulanceCompany(bid, -1);
        //TODO Insert call to get a different amb company
        return "success";
    }

    @RequestMapping(value={"/getNewBookings"}, method = RequestMethod.GET   )
    public @ResponseBody String getNewUnapprovedBookings(@ModelAttribute("bookingId") String bookingId, BindingResult result) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        UserObj userObj = userObjDAO.getUserByUsername(name);

        AmbulanceCompany ambCompany = ambulanceCompanyDAO.getCompanyUserId(userObj.getId());

        List<AmbulanceBooking> listBookings = ambulanceBookingDAO.getNewBookingsForAmbCompany(ambCompany.getId());
        if(listBookings.size() > 0)
        {
            Gson gson = new Gson();
            String json = gson.toJson(listBookings);
            return json;
        }
        return "none";
    }

}
