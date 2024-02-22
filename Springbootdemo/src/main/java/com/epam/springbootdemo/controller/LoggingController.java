package com.epam.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.springbootdemo.modal.User;
import com.epam.springbootdemo.service.AuthenticationSystem;
@Controller
public class LoggingController {
    @Autowired
    AuthenticationSystem authenticationSystem;
	@GetMapping("User")
	public String UserLoginPage() {
		 return "UserLogin";
	}
	
	@GetMapping("Admin")
	public String AdminLoginPage() {
		 return "AdminLogin";
	}
	@RequestMapping("AdminDashBoard")
	public ModelAndView AdminDashBoard(String username,String password) {
		User user=new User();
		user.setUserType("Admin");
		user.setUserName(username);user.setPassword(password);
		ModelAndView mv=new ModelAndView();
		mv.setViewName("AdminDashBoard");
		mv.addObject("User",authenticationSystem.login(user));
		mv.addObject("message","Login SuccessFull");
		
		 return mv;
	}
	@RequestMapping("userDashboard")
	public ModelAndView userDashboard(String username,String password) {
		User user=new User();
		user.setUserType("User");
		user.setUserName(username);
		user.setPassword(password);
		ModelAndView mv=new ModelAndView();
		mv.setViewName("UserDashboard");
		mv.addObject("User",authenticationSystem.login(user));
		mv.addObject("message","Login SuccessFul");
		
		 return mv;
	}
}
