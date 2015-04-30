package com.abs.controller;

import com.abs.domain.AmbulanceBooking;
import com.abs.domain.AmbulanceCompany;
import com.abs.domain.UserObj;
import com.abs.service.AmbulanceBookingDAO;
import com.abs.service.AmbulanceCompanyDAO;
import com.abs.service.UserObjDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.Date;
import java.util.List;


@Controller
public class LoginController {

	@Autowired
	AmbulanceBookingDAO ambulanceBookingDAO;
	@Autowired
	AmbulanceCompanyDAO ambulanceCompanyDAO;
	@Autowired
	UserObjDAO userObjDAO;

		@RequestMapping(value={"/home","AmbulanceBookingSystem/home"})
		public String showHomePage(ModelMap model) {
			model.addAttribute("title","Home");
			Date date = new java.util.Date();
			model.addAttribute("message", "This is the ABS Homepage.");
			model.addAttribute("now", date);


			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String name = auth.getName();
			if(auth.getName().equals("anonymousUser"))
			{
				return "notLoggedIn";
			}

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

			return "home";

	}

	@RequestMapping(value={"/","/login","AmbulanceBookingSystem/login"}, method = RequestMethod.GET)
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {



		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		model.addObject("title","Login");

		return model;
	}
}
