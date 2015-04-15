package com.abs.controller;

/**
 * Created by Declan on 09/02/2015.
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;

import com.abs.domain.*;
import com.abs.service.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ambbooking")
public class BookingController {

    @Autowired
    AmbulanceBookingDAO ambulanceBookingDAO;
    @Autowired
    PatientDAO patientDAO;
    @Autowired
    UserObjDAO userObjDAO;
    @Autowired
    AmbulanceCompanyDAO ambulanceCompanyDAO;
    @Autowired
    AmbulanceCrewDAO ambulanceCrewDAO;
    @Autowired
    LocationDAO locationDAO;
    @Autowired
    WardDAO wardDAO;
    @Autowired
    private ServletContext servletContext;

    @RequestMapping(value={"/displayBookings"}, method = RequestMethod.GET)
    public String listAll(ModelMap model) {

        List<AmbulanceBooking> listBookings = ambulanceBookingDAO.getAllBookings();
        List<AmbulanceCrew> listCrews = ambulanceCrewDAO.getAllCrews();
        List<AmbulanceCompany> listCompanies = ambulanceCompanyDAO.getAllCompanies();
        model.addAttribute("companies",listCompanies);
        model.addAttribute("crews", listCrews);
        model.addAttribute("bookings", listBookings);

        Map<Integer,String> listPatients = new HashMap<Integer,String>();
        Map<Integer,String> listUsers = new HashMap<Integer,String>();

        for(AmbulanceBooking b : listBookings)
        {
            Patient p = patientDAO.getPatient(b.getPatientId());
            String patientName = p.getFirstName() + " " + p.getLastName();
            listPatients.put(b.getBookingId(),patientName );
            UserObj u = userObjDAO.getUser(b.getCreatedBy());
            String usersName = u.getFirstName() + " " + u.getLastName();
            listUsers.put(u.getId(),usersName);

            if(!b.getApprovedBy().equals(null) && !b.getApprovedBy().equals(-1) )
            {
                u = userObjDAO.getUser(b.getApprovedBy());
                usersName = u.getFirstName() + " " + u.getLastName();
                listUsers.put(u.getId(),usersName);
            }
        }

        model.addAttribute("patients", listPatients);
        model.addAttribute("users", listUsers);
        List<Location> listLoc = locationDAO.getAllLocations();
        model.addAttribute("locations", listLoc);
        return "displayBookings";
    }

/*    @RequestMapping(value="/displayBooking", method=RequestMethod.GET)
    public String displayBooking(ModelMap model){
        return "displayBooking";
    }

    @RequestMapping(value="/displayBooking/id/{id}", method=RequestMethod.GET)
    public String displayBookingByBookingNumber(@PathVariable Integer id,
                                                ModelMap model){
        AmbulanceBooking booking=ambulanceBookingDAO.getBooking(id);
        model.addAttribute("ambulancebooking", booking);
        return "displayBooking";
    }*/

    @RequestMapping(value = "/addNewBooking", method = RequestMethod.GET)
    public String addNewBooking(ModelMap model) {
        List<Ward> wardList = wardDAO.getAllWards();
        model.addAttribute("wardList", wardList);
        List<Location> locationList = locationDAO.getAllLocations();
        model.addAttribute("locationList", locationList);
        model.addAttribute("ambulancebooking", new AmbulanceBooking());
        return "addNewBooking";
    }

    @RequestMapping(value = "/addNewBooking", method = RequestMethod.POST)
    public String addBooking(@ModelAttribute("ambulancebooking")  AmbulanceBooking ambulancebooking,
                             BindingResult result, ModelMap model) {

        if(result.hasErrors())
        {
            System.out.println("ERROR1");
            return "error";
        }

        Integer id;

        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if(auth.getName().equals("anonymousUser"))
            {
                model.addAttribute("error","You must be logged in to make a booking request!");
                return "login";
            }
            String name = auth.getName();
            UserObj userObj = userObjDAO.getUserByUsername(name);

            id= ambulanceBookingDAO.createAmbulanceBookingGetId(ambulancebooking.getPatientId(), userObj.getId(),
                    ambulancebooking.getDestination(), ambulancebooking.getOrigin(), ambulancebooking.isCardiac(), ambulancebooking.isUrgent(),
                    ambulancebooking.getDateOfTransfer());

            model.addAttribute("id", id);

        } catch (Exception e) {
            System.out.println("ERROR2 ");
            e.printStackTrace();
            model.addAttribute("message", "Creation of booking failed, " + e.getLocalizedMessage());
            return "error";

        }
        List<AmbulanceBooking> listBookings = ambulanceBookingDAO.getAllBookings();
        model.addAttribute("bookings", listBookings);
        Map<Integer,String> listPatients = new HashMap<Integer,String>();
        Map<Integer,String> listUsers = new HashMap<Integer,String>();

        for(AmbulanceBooking b : listBookings)
        {
            Patient p = patientDAO.getPatient(b.getPatientId());
            String patientName = p.getFirstName() + " " + p.getLastName();
            listPatients.put(b.getBookingId(),patientName );

            UserObj u = userObjDAO.getUser(b.getCreatedBy());
            String usersName = u.getFirstName() + " " + u.getLastName();
            listUsers.put(u.getId(),usersName);

            if(!b.getApprovedBy().equals(null) && !b.getApprovedBy().equals(-1) )
            {
                u = userObjDAO.getUser(b.getApprovedBy());
                usersName = u.getFirstName() + " " + u.getLastName();
                listUsers.put(u.getId(),usersName);
            }
        }

        model.addAttribute("patients", listPatients);
        model.addAttribute("users", listUsers);
        List<Location> listLoc = locationDAO.getAllLocations();
        model.addAttribute("locations", listLoc);
        List<AmbulanceCrew> listCrews = ambulanceCrewDAO.getAllCrews();
        List<AmbulanceCompany> listCompanies = ambulanceCompanyDAO.getAllCompanies();
        model.addAttribute("companies",listCompanies);
        model.addAttribute("crews", listCrews);
        return "displayBookings";
    }

    @RequestMapping(value={"/getPatientsInWard"}, method = RequestMethod.GET   )
    public @ResponseBody String getPatientsInWard(@ModelAttribute("wardId") String wardId,BindingResult result) {
        Integer wid = Integer.parseInt(wardId);
        List<Patient> listPatients = patientDAO.getAllPatientsFromWard(wid);
        Gson gson = new Gson();
        String json = gson.toJson(listPatients);
        return json;
    }

    @RequestMapping(value={"/bookingPermission"}, method = RequestMethod.GET)
    public String bookingPermission(ModelMap model) {

        List<AmbulanceBooking> listBookings = ambulanceBookingDAO.getAllUnapprovedBookings();
        model.addAttribute("bookings", listBookings);
        String bookingIdArray = " ";
        Map<Integer,String> listPatients = new HashMap<Integer,String>();
        Map<Integer,String> listUsers = new HashMap<Integer,String>();

        for(AmbulanceBooking b : listBookings)
        {
            bookingIdArray += b.getBookingId() + ", ";
            Patient p = patientDAO.getPatient(b.getPatientId());
            String patientName = p.getFirstName() + " " + p.getLastName();
            listPatients.put(b.getBookingId(),patientName );

            UserObj u = userObjDAO.getUser(b.getCreatedBy());
            String usersName = u.getFirstName() + " " + u.getLastName();
            listUsers.put(b.getBookingId(),usersName);
        }

        bookingIdArray = bookingIdArray.substring(0, bookingIdArray.length()-1);
        model.addAttribute("bookingIdArray",bookingIdArray);
        model.addAttribute("patients", listPatients);
        model.addAttribute("users", listUsers);
        List<Location> listLoc = locationDAO.getAllLocations();
        model.addAttribute("locations", listLoc);
        model.addAttribute("numberOfBookings", listBookings.size());
        return "bookingPermission";
    }

    @RequestMapping(value={"/acceptBooking"}, method = RequestMethod.POST   )
    public @ResponseBody String acceptBooking(@ModelAttribute("bookingId") String bookingId, BindingResult result) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        if(auth.getName().equals("anonymousUser"))
        {
            return "notLoggedIn";
        }

        UserObj userObj = userObjDAO.getUserByUsername(name);
        Integer bid = Integer.parseInt(bookingId);
        ambulanceBookingDAO.setApproval(bid, true, userObj.getId());
        List<AmbulanceCompany> ambComps =  ambulanceCompanyDAO.getAllCompanies();
        int randomId = new Random().nextInt(ambComps.size());//TODO Link to broker system
        ambulanceBookingDAO.setAmbulanceCompany(bid, ambComps.get(randomId).getId());
        return "success";
    }

    @RequestMapping(value={"/denyBooking"}, method = RequestMethod.POST   )
    public @ResponseBody String denyBooking(@ModelAttribute("bookingId") String bookingId, BindingResult result) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        if(auth.getName().equals("anonymousUser"))
        {
            return "notLoggedIn";
        }
        UserObj userObj = userObjDAO.getUserByUsername(name);
        Integer bid = Integer.parseInt(bookingId);
        ambulanceBookingDAO.setApproval(bid, false, userObj.getId());
        return "success";
    }

    @RequestMapping(value={"/getNewUnapprovedBookings"}, method = RequestMethod.GET   )
    public @ResponseBody String getNewUnapprovedBookings(@ModelAttribute("bookingId") String bookingId, BindingResult result) {

        List<AmbulanceBooking> listBookings = ambulanceBookingDAO.getAllUnapprovedBookings();
        Gson gson = new Gson();
        String json = gson.toJson(listBookings);
        return json;
    }

/*    @RequestMapping(value="/modifyBooking", method = RequestMethod.GET)
    public String modify(ModelMap model) {
        List<AmbulanceBooking> bookings=ambulanceBookingDAO.getAllBookings();
        model.addAttribute("bookings", bookings);
        return "modifyBooking";
    }

    @RequestMapping(value ="/modifyBookingForm/id/{id}", method = RequestMethod.GET)
    public String modifyBookingByID(@PathVariable Integer id, ModelMap model) {
        AmbulanceBooking bookingModify=ambulanceBookingDAO.getBooking(id);
        model.addAttribute("message", "Booking with id "+ id +" can now be modified");
        model.addAttribute("booking", bookingModify);
        return "modifyBookingForm";
    }

    @RequestMapping(value = "/removeBooking", method = RequestMethod.GET)
    public String deleteBooking(ModelMap model) {
        List<AmbulanceBooking> listBookings=ambulanceBookingDAO.getAllBookings();
        model.addAttribute("bookings", listBookings);
        return "removeBooking";
    }

    @RequestMapping(value ="/removeBooking/id/{id}", method = RequestMethod.GET)
    public String deleteBookingById(@PathVariable Integer id, ModelMap model) {
        AmbulanceBooking bookingDelete = ambulanceBookingDAO.getBooking(id);
        ambulanceBookingDAO.deleteAmbulanceBooking(id);
        model.addAttribute("message", "Booking  has been deleted from the system");
        List<AmbulanceBooking> listBookings = ambulanceBookingDAO.getAllBookings();
        model.addAttribute("bookings", listBookings);
        return "removeBooking";
    }*/
}