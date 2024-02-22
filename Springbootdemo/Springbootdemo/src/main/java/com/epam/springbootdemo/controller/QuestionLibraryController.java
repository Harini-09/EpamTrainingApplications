package com.epam.springbootdemo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.springbootdemo.modal.Question;
import com.epam.springbootdemo.modal.User;
import com.epam.springbootdemo.service.QuestionLibraryService;

@Controller
public class QuestionLibraryController {
    @Autowired
    QuestionLibraryService questionLibraryService;
	@GetMapping("CreateQuestion")
	public String createQuestion() {
		 return "CreateQuestion";
	}
	@GetMapping("UpdateQuestion")
	public ModelAndView updateQuestion() {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("UpdateQuestion");
		mv.addObject("Questions",questionLibraryService.getAlldata());
		 return mv ;
	} 

	@RequestMapping("ViewAllQuestions")
	public ModelAndView viewAllQuestions(String username,String password) {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("ViewAllQuestions");
		mv.addObject("Questions",questionLibraryService.getAlldata());
		mv.addObject("message","Questions Available in Question Library:");
		
		 return mv;
	}	
	
	@PostMapping("addQuestion")
	public ModelAndView addQuestion(@ModelAttribute("question")Question question) {
	    String[] arrayOfOptions=question.getOptions().get(0).split("\r\n");
	    question.setOptions(Arrays.asList(arrayOfOptions));
		ModelAndView mv=new ModelAndView();
		if(questionLibraryService.viewList(question.getQuestionID())==null) {
		mv.setViewName("QuestionCreation");
		mv.addObject("Questions",questionLibraryService.addQuestion(question));
		mv.addObject("message","Questions Available in Question Library:");
		}
		else {
			mv.setViewName("Error");
			mv.addObject("message","Question ID Already Exists.........Please Enter Valid Question ID!!! ");
		}
		
		 return mv;
	}
	
	@RequestMapping("QuestionUpdation")
	public ModelAndView updateQuestion(int questionID,int choice,String updatedvalue) {
        
		ModelAndView mv=new ModelAndView();
		if(questionLibraryService.viewList(questionID)==null) {
			mv.setViewName("Error");
			mv.addObject("message","Question Not found..Please Enter Valid Question ID!!! ");
		}
		else {
		if(choice!=2 & choice>=1 && choice<=5) {
		mv.setViewName("Success");
		mv.addObject("Questions",questionLibraryService.modifyQuestion((Object)updatedvalue, questionID,choice));
		mv.addObject("message","Question Updated SuccessFully:");
		}
		else if(choice==2) {
		String[] optionList=updatedvalue.split(",");
		List<String>updatedOptions=Arrays.asList(optionList);
		mv.setViewName("Success");
		mv.addObject("Questions",questionLibraryService.modifyQuestion((Object)updatedOptions, questionID,choice));
		mv.addObject("message","Question Updated SuccessFully:");
		}
		else {
			mv.setViewName("Error");
			mv.addObject("message","Please Enter Valid Choice!!! ");
		}
		}
		
		 return mv;
	}
	
	@GetMapping("DeleteQuestion")
	public ModelAndView deleteQuestion() {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("DeleteQuestion");
		mv.addObject("Questions",questionLibraryService.getAlldata());
		mv.addObject("message","Questions Available in Question Library:");
		
		 return mv;
	}
	@RequestMapping("deleteQuestionByID")
	public ModelAndView deleteQuestionByID(int id) {
		ModelAndView mv=new ModelAndView();
		
		boolean flag=(questionLibraryService.viewList(id)!=null);
		if(flag) {
		mv.setViewName("Success");
		mv.addObject("Questions",questionLibraryService.deleteQuestion(id));
		mv.addObject("message","Questions Deleted SuccessFully:");
		}
		else {
		mv.setViewName("Error");
		mv.addObject("message","Question Not found..Please Enter Valid Question ID!!! ");
		}
		
		 return mv;
	}

}
