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
import com.epam.service.QuizAllotment;
import com.epam.service.QuizLibraryService;
import com.epam.service.UserAuthenticationImpl;
import com.epam.customexceptions.InvalidQuizIdEntryException;
import com.epam.dtos.UserDto;

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
		
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@RequestMapping("validateAdmin")
	public ModelAndView validateAdmin(String id,String password) {
		UserDto userDto = new UserDto(id,password,"user");
		
		userDto.setType("admin");
		ModelAndView mv = new ModelAndView();
		if(authentication.logIn(userDto)) {
			mv.setViewName("showoperations");
		}
		else {
			mv.addObject(invaliduser, "Incorrect Credentials!!");
			mv.setViewName("errorinuser");
		}
		return mv;
			
	}
	
	@RequestMapping("validateuser")
	public ModelAndView validateuser(UserDto userDto) {
		userDto.setType("user");
		ModelAndView mv = new ModelAndView();
		if(authentication.logIn(userDto)) {
			mv.setViewName("showquizforuser");
		}
		else {
			mv.addObject(invaliduser, "Incorrect Credentials!!");
			mv.setViewName("errorinuser");
		}
		return mv;
			
	}
	
	@RequestMapping("registeruser")
	public ModelAndView registeruser(UserDto userDto) {
		
		userDto.setType("user");
		ModelAndView mv = new ModelAndView();
		authentication.signUp(userDto);
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
	public ModelAndView takeQuiz(int quizID) throws InvalidQuizIdEntryException {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("quiz");
		mv.addObject("Quizes",quizAllotment.allotQuiz(quizID));
		mv.addObject(message,"Quizes Available in Quiz Library:");
		return mv;
	}
	
	@GetMapping("submitQuiz")
	public ModelAndView submitQuiz(@RequestParam List<String> answers,@RequestParam int quizID) throws InvalidQuizIdEntryException {
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
