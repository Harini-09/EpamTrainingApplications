package com.epam.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.customexceptions.InvalidQuestionIdEntryException;
import com.epam.customexceptions.InvalidQuizIdEntryException;
import com.epam.customexceptions.NoSuchQuestionExistInQuizException;
import com.epam.entities.Question;
import com.epam.entities.Quiz;
import com.epam.service.QuizLibraryService;

@Controller
public class QuizController {
	
	@Autowired
	QuizLibraryService service;
	
	String error = "error";
	String message = "message";
	String quizid1 = "quizid";
	String questionid1 = "questionid";
	
	@PostMapping("addquiz")
	public ModelAndView createquiz(String title, String quizQuestions, int totalMarks) {
		ModelAndView mv=new ModelAndView();
		String[] questionsArray=quizQuestions.split(",");
		List<Question> questionList=new ArrayList<>();
		for(String questionID:questionsArray) {
		questionList.add(service.addNewQuestion(Integer.parseInt(questionID)));
		}
		Quiz quiz=new Quiz(title,questionList,totalMarks);
		mv.setViewName("quizcreated");
		service.insert(quiz);
		mv.addObject(message,"Quiz Added Successfully:");
		 return mv;
	}
	
	@PostMapping("modifymarksinquiz")
	public ModelAndView modifymarksinquiz(int id,int marks) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("quizmarksmodified");
		mv.addObject(quizid1,id);
		try {
			service.isQuizTitlePresent(id);
			service.updateMarks(id, marks);
		}catch (InvalidQuizIdEntryException e) {
			mv.setViewName(error);
			mv.addObject(message, e.getMessage());
		}
		return mv;
	}
	
	@PostMapping("addquestiontoquiz")
	public ModelAndView addquestiontoquiz(int quizid,int questionid) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("questionaddedtoquiz");
		try {
			service.isQuizTitlePresent(quizid);
			service.isQuestionIdPresent(questionid);
			service.addQuestion(quizid, questionid);
			mv.addObject(questionid1, questionid);
			mv.addObject(quizid1, quizid);
		}catch (InvalidQuestionIdEntryException | InvalidQuizIdEntryException e) {
			mv.addObject(message, e.getMessage());
			mv.setViewName(error);	
		}
		return mv;	
	}
	
	@PostMapping("removequestionfromquiz")
	public ModelAndView removequestionfromquiz(int quizid,int questionid) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("questionremovedfromquiz");
		try {
			service.isQuizTitlePresent(quizid);
			service.isQuestionInQuizPresent(quizid, questionid);
			service.removeQuestion(quizid, questionid);
			mv.addObject(quizid1, quizid);
			mv.addObject(questionid1, questionid);
		}catch (NoSuchQuestionExistInQuizException | InvalidQuizIdEntryException e) {
			mv.setViewName(error);
			mv.addObject(message, e.getMessage());
		}
		return mv;
	}
	
	@PostMapping("deletequiz")
	public ModelAndView deletequiz(int id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("quizremoved");
		try {
			service.isQuizTitlePresent(id);
			service.delete(id);
			mv.addObject(quizid1, id);
		}catch (InvalidQuizIdEntryException e) {
			mv.setViewName(error); 
			mv.addObject(message, e.getMessage());
		}
		return mv;
	}
	
	@RequestMapping("displayquiz")
	public ModelAndView viewquiz(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("viewquiz");
		mv.addObject("quizes",service.view());
		mv.addObject(message,"Display Quiz");
		return mv;
	}
	
}
