package com.abs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class LoginController {

		@RequestMapping(value={"/home","AmbulanceBookingSystem/home"})
		public String showHomePage(ModelMap model) {
			Date date = new java.util.Date();
			model.addAttribute("message", "This is the ABS Homepage.");
			model.addAttribute("now", date);
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

		return model;
	}
}
