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
        List<AmbulanceCrew> listCrews = ambulanceCrewDAO.getAllCrewsFromAmbCompany(ambCompany.getId());
        model.addAttribute("crews", listCrews);

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

        Gson gson = new Gson();
        String json = gson.toJson(listBookings);
        model.addAttribute("jsonBookingArray",json);

        return "bookingStandby";
    }

    @RequestMapping(value={"/cancelBooking"}, method = RequestMethod.POST   )
    public @ResponseBody String cancelBooking(@ModelAttribute("bookingId") String bookingId, BindingResult result) {
        Integer bid = Integer.parseInt(bookingId);
        ambulanceBookingDAO.setAmbulanceCompany(bid, -1);
        ambulanceBookingDAO.setAmbulanceCrew(bid, -1);
        //TODO Insert call to get a different amb company
        return "success";
    }

    @RequestMapping(value={"/getNewBookings"}, method = RequestMethod.GET   )
    public @ResponseBody String getBookings(@ModelAttribute("bookingId") String bookingId, BindingResult result) {

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

    @RequestMapping(value={"/assignCrew"}, method = RequestMethod.POST   )
    public @ResponseBody String assignCrew(@ModelAttribute("bookingId") String bookingId,
                                           @ModelAttribute("crewId") String crewId, BindingResult result) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        if(auth.getName().equals("anonymousUser"))
        {
            return "notLoggedIn";
        }
        Integer cid = Integer.parseInt(crewId);
        Integer bid = Integer.parseInt(bookingId);

        //TODO Add check to see if crew is busy

        ambulanceBookingDAO.setStatus(bid,2);
        ambulanceBookingDAO.setAmbulanceCrew(bid, cid);
        return "success";
    }

    @RequestMapping(value={"/ambCrewApp"}, method = RequestMethod.GET)
    public String ambMobileApp(ModelMap model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        UserObj userObj = userObjDAO.getUserByUsername(name);

        AmbulanceCrew ac = ambulanceCrewDAO.getCrewUserId(userObj.getId());

        List<AmbulanceBooking> listBookings = ambulanceBookingDAO.getAllAmbCrewBookings(ac.getId());
        model.addAttribute("bookings", listBookings);
        String bookingIdArray = " ";
        Map<Integer,String> listPatients = new HashMap<Integer,String>();

        for(AmbulanceBooking b : listBookings)
        {
            bookingIdArray += b.getBookingId() + ", ";
            Patient p = patientDAO.getPatient(b.getPatientId());
            String patientName = p.getFirstName() + " " + p.getLastName();
            listPatients.put(b.getBookingId(), patientName);
        }

        bookingIdArray = bookingIdArray.substring(0, bookingIdArray.length()-1);
        model.addAttribute("bookingIdArray",bookingIdArray);
        model.addAttribute("patients", listPatients);
        List<Location> listLoc = locationDAO.getAllLocations();
        model.addAttribute("locations", listLoc);
        model.addAttribute("numberOfBookings", listBookings.size());

        return "ambCrewApp";
    }

    @RequestMapping(value={"/getNewCrewBookings"}, method = RequestMethod.GET   )
    public @ResponseBody String getNewCrewBookings(@ModelAttribute("bookingId") String bookingId, BindingResult result) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        UserObj userObj = userObjDAO.getUserByUsername(name);
        AmbulanceCrew ac = ambulanceCrewDAO.getCrewUserId(userObj.getId());

        List<AmbulanceBooking> listBookings = ambulanceBookingDAO.getAllAmbCrewBookings(ac.getId());

        Gson gson = new Gson();
        String json = gson.toJson(listBookings);
        return json;
    }

    @RequestMapping(value={"/setBookingStatus"}, method = RequestMethod.POST   )
    public @ResponseBody String setBookingStatus(@ModelAttribute("bookingId") String bookingId,
                                                 @ModelAttribute("status") String status, BindingResult result) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        if(auth.getName().equals("anonymousUser"))
        {
            return "notLoggedIn";
        }
        Integer statusId = Integer.parseInt(status);
        Integer bid = Integer.parseInt(bookingId);

        //TODO Add check to see if crew is busy

        ambulanceBookingDAO.setStatus(bid,statusId);
        return "success";
    }
}
