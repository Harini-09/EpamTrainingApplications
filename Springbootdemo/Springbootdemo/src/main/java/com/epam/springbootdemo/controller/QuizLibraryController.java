package com.epam.springbootdemo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.springbootdemo.modal.Question;
import com.epam.springbootdemo.modal.Quiz;
import com.epam.springbootdemo.service.QuestionLibraryService;
import com.epam.springbootdemo.service.QuizLibraryService;

@Controller
public class QuizLibraryController {
	@Autowired
	QuizLibraryService quizService;
	@Autowired
	QuestionLibraryService questionService;
	@RequestMapping("ViewAllQuizes")
	public ModelAndView viewAllQuiz(String username,String password) {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("ViewQuizes");
		mv.addObject("Quizes",quizService.getAlldata());
		mv.addObject("message","Quizes Available in Quiz Library:");
		
		 return mv;
	}	
	@GetMapping("DeleteQuiz")
	public ModelAndView deleteQuiz() {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("DeleteQuiz");
		mv.addObject("Quizes",quizService.getAlldata());
		mv.addObject("message","Quizes Available in Quiz Library:");
		
		 return mv;
	}
	@RequestMapping("deleteQuizByID")
	public ModelAndView deleteQuestionByID(int quizID) {
		ModelAndView mv=new ModelAndView();
		if(quizService.view(quizID)!=null) {
		mv.setViewName("Success");
		mv.addObject("Quizes",quizService.deleteQuiz(quizID));
		mv.addObject("message","Quiz Deleted SuccessFully:");
		}
		else {
			mv.setViewName("Error");
			mv.addObject("message","Quiz ID doents Exists.........Please Enter Valid Quiz ID!!! ");
		}
		
		 return mv;
	}
	@PostMapping("addQuiz")
	public ModelAndView addQuiz(int quizID,String title,int totalMarks,String quizQuestions) {
		ModelAndView mv=new ModelAndView();
	    if(quizService.view(quizID)!=null) {
	    	mv.setViewName("Error");
			mv.addObject("message","Quiz ID Already Exists.........Please Use Another Quiz ID!!! ");
	    }
	    else {
		String[] questions=quizQuestions.split(",");
		List<Question>questionList=new ArrayList<>();
		for(String questionID:questions) {
			
			if(questionService.viewList(Integer.parseInt(questionID))!=null){
				questionList.add((Question) questionService.viewList(Integer.parseInt(questionID)));
			}
			else {
				mv.setViewName("Error");
				mv.addObject("message","Question ID doesnt Exists.........Please Use Valid Question ID!!! ");
				return mv;
			}
		
		}
		Quiz quiz=new Quiz(quizID,title,totalMarks,questionList);
	
		mv.setViewName("Success");
	
		mv.addObject("Quizes",quizService.addQuiz(quiz));
		mv.addObject("message","Quiz Added Successfully:");
	    }
		 return mv;
	}
	@GetMapping("createQuiz")
	public ModelAndView createQuestion() {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("CreateQuiz");
		mv.addObject("Questions",questionService.getAlldata());
		 return mv;
	}
	@GetMapping("updateQuiz")
	public ModelAndView updateQuiz(int quizID) {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("UpdateQuiz");
		mv.addObject("Quiz",quizService.view(quizID));
		mv.addObject("Questions",questionService.getAlldata());
		 return mv;
	}
//	@GetMapping("updateQuizTitleorMarks")
//	public String updateQuizTitleorMarks() {
//		 return "UpdateTitleorTotalmarks";
//	}
	@RequestMapping("QuizUpdation")
	public ModelAndView updateQuestion(int quizID,int choice,String updatedvalue) {
        
		ModelAndView mv=new ModelAndView();
		if(quizService.view(quizID)==null) {
			mv.setViewName("Error");
			mv.addObject("message","Quiz ID doents Exists.........Please Enter Valid Quiz ID!!! ");
		}
		else {
		mv.setViewName("Success");
		if(choice==1) {
		mv.addObject("Quiz",quizService.updateQuiz(quizID, updatedvalue, choice));
		}
		else if(choice==2) {
		mv.addObject("Quiz",quizService.updateQuiz(quizID, Integer.parseInt(updatedvalue), choice));
		}
		else if(choice==3) {
		mv.addObject("Quiz",quizService.addQuestion(Integer.parseInt(updatedvalue), quizID));
		}
		else {
		mv.addObject("Quiz",quizService.deleteQuestion(Integer.parseInt(updatedvalue), quizID));
		}
		mv.addObject("message","Quiz Updated SuccessFully...!!");
		}
		 return mv;
	}

}
