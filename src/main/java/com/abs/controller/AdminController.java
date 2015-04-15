package com.abs.controller;

import com.abs.domain.*;
import com.abs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import java.util.List;

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

    @RequestMapping(value = "/addNewCompany", method = RequestMethod.GET)
    public String addNewCompany(ModelMap model) {
        model.addAttribute("ambulancecompany", new AmbulanceCompany());
        model.addAttribute("user", new UserObj());
        return "addNewCompany";
    }
}
