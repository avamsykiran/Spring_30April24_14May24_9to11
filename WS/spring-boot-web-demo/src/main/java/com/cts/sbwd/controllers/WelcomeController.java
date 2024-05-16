package com.cts.sbwd.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {

	@RequestMapping("/")
	public ModelAndView homeAction() {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("msg", "Hello! Welcoem To My Spring Boot Web Application");
		mv.setViewName("home-page");
		
		return mv;
	}
}
