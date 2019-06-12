package com.dano.classDiaryApplication.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.dano.classDiaryApplication.model.User;
import com.dano.classDiaryApplication.service.UserService;

@Controller
public class AuthenticationController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index"); // resources/template/index.html
		return modelAndView;
	}			
	
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login"); // resources/template/login.html
		return modelAndView;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)  
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView();
		 User user = new User();
		 modelAndView.addObject("user", user); 
		 modelAndView.setViewName("register"); // resources/template/register.html
		return modelAndView;
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home"); // resources/template/home.html
		return modelAndView;
	}
	
	@RequestMapping(value = "/director", method = RequestMethod.GET)
	public ModelAndView directorHome(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		
		String lastLoggedUserEmail = "";
		String welcomeText = "";
		
		HttpSession session = request.getSession(false);
		if(session != null) {
		lastLoggedUserEmail = (String) session.getAttribute("email");
		welcomeText = "Hello" + lastLoggedUserEmail;
		}
		modelAndView.addObject("email", lastLoggedUserEmail);
		modelAndView.addObject("welcomeText", welcomeText);
		modelAndView.setViewName("director"); // resources/template/director.html
		return modelAndView;
	}
	
	@RequestMapping(value = "/teacher", method = RequestMethod.GET)
	public ModelAndView teacherHome() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("teacher"); // resources/template/teacher.html
		return modelAndView;
	}
	
	@RequestMapping(value = "/parent", method = RequestMethod.GET)
	public ModelAndView parentHome() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("parent"); // resources/template/parent.html
		return modelAndView;
	}
	
	@RequestMapping(value = "/register", method=RequestMethod.POST)
	public ModelAndView registerUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap){
		ModelAndView modelAndView = new ModelAndView();
		// Check for the validation
		if (bindingResult.hasErrors()) {
			modelAndView.addObject("successMessage", "Popraw błędy w formie!");
			modelMap.addAttribute("bindingResult", bindingResult);
		}
		else if (userService.isUserAlreadyPresent(user)) {	
			modelAndView.addObject("successMessage", "Użytkownik taki już istnieje!");
		}
		// we will save the user if, no binding errors
		else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "Rejestracja przebiegła pomyślnie");
			
		}
		
		modelAndView.addObject("user", new User());
		modelAndView.setViewName("register");
		return modelAndView;
	}
}
