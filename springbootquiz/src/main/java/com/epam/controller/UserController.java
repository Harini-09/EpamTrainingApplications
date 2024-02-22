package com.epam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.entities.Question;
import com.epam.entities.Quiz;
import com.epam.entities.User;
import com.epam.service.QuizAllotment;
import com.epam.service.QuizLibraryService;
import com.epam.service.UserAuthenticationImpl;

@Controller
public class UserController {
	
	@Autowired
	UserAuthenticationImpl authentication;
	
	@Autowired
	QuizAllotment quizAllotment;
	
	@Autowired
	QuizLibraryService quizService;
	
	private String message = "message";
	
	private String invaliduser = "invaliduser";
		
	@RequestMapping("/")   //https://localhost:8080/     Only this method we are calling.All the remaining methods are called from action of .jsp pages. 
	public String home() {								// Here .jsp pages became clients
		return "home";      //view
	}
	
	@RequestMapping("validateAdmin")       /*  https://localhost:8080/validateAdmin       <-request url from client. Here validateAdmin will be appended to Url and due to mapping it comes here.     */
	public ModelAndView validateAdmin(String id,String password) {
		User user = new User(id,password,"user");
		user.setType("admin");
		ModelAndView mv = new ModelAndView();   //mv - model and a view
		if(authentication.logIn(user)) {
			mv.setViewName("showoperations");    //v - setting the view name
		}
		else {
			mv.addObject(invaliduser, "Incorrect Credentials!!");   //m - the model (adding the object)
			mv.setViewName("errorinuser");
		} 
		return mv;    //This is the unformatted response to the Dispatcher Servlet which forwards it to View Resolver
			
	}
	
	@RequestMapping("validateuser")
	public ModelAndView validateuser(User user) {
		user.setType("user");
		ModelAndView mv = new ModelAndView();
		if(authentication.logIn(user)) {
			mv.setViewName("showquizforuser");
		}
		else {
			mv.addObject(invaliduser, "Incorrect Credentials!!");
			mv.setViewName("errorinuser");
		}
		return mv;
			
	}
	
	@RequestMapping("registeruser")
	public ModelAndView registeruser(User user) {
		user.setType("user");
		ModelAndView mv = new ModelAndView();
		authentication.signUp(user);
		mv.setViewName("showquizforuser");
		return mv;
	}
	
	@GetMapping("viewquizesavailable")
	public ModelAndView viewQuizes() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("availablequizes");
		mv.addObject("quizes",quizService.view());
		mv.addObject(message,"Quizes Available in Quiz Library:");
		return mv;
	}
	
	@GetMapping("TakeQuiz")
	public ModelAndView takeQuiz(int quizID) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("quiz");
		mv.addObject("Quizes",quizAllotment.allotQuiz(quizID));
		mv.addObject(message,"Quizes Available in Quiz Library:");
		return mv;
	}
	
	@GetMapping("submitQuiz")
	public ModelAndView submitQuiz(@RequestParam List<String> answers,@RequestParam int quizID) {
		ModelAndView mv=new ModelAndView();
		Quiz quiz = quizAllotment.allotQuiz(quizID);
		float totalMarks=0;
		float marksForEachQuestion=(float)quiz.getTotalMarks()/(float)quiz.getQuestionsList().size();
		int i=0;
		for(Question question:quiz.getQuestionsList()) {
			if(question.getAnswer().equals(answers.get(i++))) {
				totalMarks+=marksForEachQuestion;
			}
		}
		mv.setViewName("userlogout");
		mv.addObject(message,"Your Total Score:"+totalMarks); 
		return mv;
	
	}
	
}
