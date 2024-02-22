package com.epam.springbootdemo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.springbootdemo.modal.Question;
import com.epam.springbootdemo.modal.Quiz;
import com.epam.springbootdemo.service.QuizLibraryService;

@Controller
public class UserController {
@Autowired
QuizLibraryService quizLibraryService;

@GetMapping("viewQuizesAvailable")
public ModelAndView createQuestion() {
	ModelAndView mv=new ModelAndView();
	mv.setViewName("AvailableQuizes");
	mv.addObject("Quizes",quizLibraryService.getAlldata());
	mv.addObject("message","Quizes Available in Quiz Library:");
	 return mv;

}
@GetMapping("TakeQuiz")
public ModelAndView takeQuiz(int quizID) {
	ModelAndView mv=new ModelAndView();
	mv.setViewName("Quiz");
	mv.addObject("Quizes",quizLibraryService.view(quizID));
	mv.addObject("message","Quizes Available in Quiz Library:");
	 return mv;

}
@GetMapping("submitQuiz")
public ModelAndView submitQuiz(@RequestParam List<String> answers,@RequestParam int quizID) {
	ModelAndView mv=new ModelAndView();
	Quiz quiz=(Quiz)quizLibraryService.view(quizID);
	float totalMarks=0;   
	float marksForEachQuestion=(float)quiz.getTotalMarks()/(float)quiz.getQuizQuestions().size();
	int i=0;
	for(Question question:quiz.getQuizQuestions()) {
		if(question.getAnswer().equals(answers.get(i++))) {
			totalMarks+=marksForEachQuestion;
		}
	}
	mv.setViewName("Success");
//	mv.addObject("totalMarks",totalMarks);
	mv.addObject("message","Thank you for taking quiz:)!!! Your Total Score:"+totalMarks);
	 return mv;

}


}
