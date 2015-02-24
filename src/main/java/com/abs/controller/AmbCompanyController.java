package com.abs.controller;

import com.abs.domain.AmbulanceBooking;
import com.abs.domain.AmbulanceCrew;
import com.abs.domain.Location;
import com.abs.domain.Patient;
import com.abs.service.AmbulanceBookingDAO;
import com.abs.service.AmbulanceCrewDAO;
import com.abs.service.LocationDAO;
import com.abs.service.PatientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    PatientDAO patientDAO;
    @Autowired
    LocationDAO locationDAO;
    @Autowired
    private ServletContext servletContext;

    @RequestMapping(value={"/bookingStandby"}, method = RequestMethod.GET)
    public String bookingPermission(ModelMap model) {

        List<AmbulanceBooking> listBookings = ambulanceBookingDAO.getNewBookingsForAmbCompany(1);//TODO Replace with id
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

        return "bookingPermission";
    }
}
