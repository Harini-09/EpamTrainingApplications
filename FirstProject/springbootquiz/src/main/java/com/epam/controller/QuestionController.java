package com.epam.controller;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.customexceptions.InvalidQuestionIdEntryException;
import com.epam.entities.Question;
import com.epam.service.QuestionLibraryService;

@Controller
public class QuestionController {

	@Autowired
	QuestionLibraryService service;

	private String errormessage = "errormessage";

	private String questionid = "questionid";

	private String errorinquestions = "errorinquestions";
	
	private String message = "message";

	@PostMapping("createquestion")
	public ModelAndView createquestion(Question question) {
		ModelAndView mv = new ModelAndView();
		service.insert(question);
		String s = "Question with questionId : " + question.getQuestionId() + ", created successfully";
		mv.addObject(message, s);
		service.insert(question);
		mv.setViewName("success");
		return mv;
	}

	@PostMapping("modifytitleforquestion")
	public ModelAndView modifytitleforquestion(int id, String title) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("questiontitlemodified");
		mv.addObject(questionid, id);
		try {
			service.isQuestionIdPresent(id);
			service.modifyTitle(id, title);
		} catch (InvalidQuestionIdEntryException e) {
			mv.setViewName(errorinquestions);
			mv.addObject(errormessage, e.getMessage());
		}
		return mv;
	}

	@PostMapping("modifyquestiondescription")
	public ModelAndView modifyquestiondescription(int id, String question) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("questiondescriptionmodified");
		mv.addObject(questionid, id);
		try {
			service.isQuestionIdPresent(id);
			service.modifyQuestion(id, question);
		} catch (InvalidQuestionIdEntryException e) {
			mv.setViewName(errorinquestions);
			mv.addObject(errormessage, e.getMessage());
		}
		return mv;
	}

	@PostMapping("modifyquestionoptions")
	public ModelAndView modifyquestionoptions(int id, @RequestParam("options") List<String> options) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("questionoptionsmodified");
		mv.addObject(questionid, id);
		try {
			service.isQuestionIdPresent(id);
			service.modifyOptions(id, options);
		} catch (InvalidQuestionIdEntryException e) {
			mv.setViewName(errorinquestions);
			mv.addObject(errormessage, e.getMessage());
		}
		return mv;
	}

	@PostMapping("modifyquestionlevel")
	public ModelAndView modifyquestionlevel(int id, String questionlevel) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("questionlevelmodified");
		mv.addObject(questionid, id);
		try {
			service.isQuestionIdPresent(id);
			service.modifyQuestionLevel(id, questionlevel);
		} catch (InvalidQuestionIdEntryException e) {
			mv.setViewName(errorinquestions);
			mv.addObject(errormessage, e.getMessage());
		}
		return mv;
	}

	@PostMapping("modifytopictag")
	public ModelAndView modifytopictag(int id, String topictags) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("questiontopictagmodified");
		mv.addObject(questionid, id);
		try {
			service.isQuestionIdPresent(id);
			service.modifyTopicTag(id, topictags);
		} catch (InvalidQuestionIdEntryException e) {
			mv.setViewName(errorinquestions);
			mv.addObject(errormessage, e.getMessage());
		}
		return mv;
	}

	@PostMapping("modifyanswer")
	public ModelAndView modifyanswer(int id, String answer) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("questionanswermodified");
		mv.addObject(questionid, id);
		try {
			service.isQuestionIdPresent(id);
			service.modifyAnswer(id, answer);
		} catch (InvalidQuestionIdEntryException e) {
			mv.setViewName(errorinquestions);
			mv.addObject(errormessage, e.getMessage());
		}
		return mv;
	}

	@PostMapping("removequestion")
	public ModelAndView removequestion(int id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("questionremoved");
		mv.addObject(questionid, id);
		try {
			service.isQuestionIdPresent(id);
			service.delete(id);
		} catch (InvalidQuestionIdEntryException e) {
			mv.setViewName(errorinquestions);
			mv.addObject(errormessage, e.getMessage());
		} catch (ConstraintViolationException e) {
			mv.setViewName(errorinquestions);
			mv.addObject(errormessage, "This question is used in the quiz library..You cannot delete it!!");
		}
		return mv;
	}

	@GetMapping("displayQuestions")
	public ModelAndView displayQuestions() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("viewQuestions");
		mv.addObject("questions", service.viewQuestions());
		mv.addObject(message, "Display Questions");
		return mv;
	}
}
