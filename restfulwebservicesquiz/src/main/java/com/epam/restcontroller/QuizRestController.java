package com.epam.restcontroller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epam.customexceptions.InvalidQuestionIdEntryException;
import com.epam.customexceptions.InvalidQuizIdEntryException;
import com.epam.customexceptions.NoSuchQuestionExistInQuizException;
import com.epam.dtos.QuizDto;
import com.epam.entities.Quiz;
import com.epam.service.QuizLibraryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/quizes")
public class QuizRestController {

	@Autowired
	QuizLibraryService service;
	
	private final Logger logger = LogManager.getLogger(QuizRestController.class);

	@PostMapping(value="/save",produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<QuizDto> saveQuiz(@RequestBody @Valid QuizDto quizDto) {
		logger.info("Received the POST request to save a question");
		return new ResponseEntity<>(service.insert(quizDto), HttpStatus.CREATED);
	}

	@GetMapping(value="/view",produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Quiz>> getQuizes() {
		logger.info("Received the GET request to retrieve all the quizzes");
		return new ResponseEntity<>(service.view(), HttpStatus.OK);
	}

	@DeleteMapping(value="delete/{quiz_id}",produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> deleteQuiz(@PathVariable("quiz_id") int id) throws InvalidQuizIdEntryException {
		logger.info("Received the DELETE request to delete a quiz");
		return new ResponseEntity<>(service.delete(id),HttpStatus.NO_CONTENT);
	}

	@PutMapping(value="questionaddition/{question_id}",produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> addQuizQuestion(@PathVariable("question_id") int id, @RequestParam int quizid) throws InvalidQuestionIdEntryException, InvalidQuizIdEntryException {
		logger.info("Received the PUT request to add a question to a quiz");
		return ResponseEntity.ok(service.addQuestion(quizid, id));
	}

	@PutMapping(value="questiondeletion/{question_id}",produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> deleteQuizQuestion(@PathVariable("question_id") int id, @RequestParam int quizid) throws InvalidQuizIdEntryException, NoSuchQuestionExistInQuizException  {
		logger.info("Received the PUT request to delete a question from a quiz");
		return ResponseEntity.ok(service.removeQuestion(quizid, id));
	}

	@PutMapping(value="marksupdation/{quiz_id}",produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> updateTotalMarks(@PathVariable("quiz_id") int id, @RequestParam int marks) throws InvalidQuizIdEntryException  {
		logger.info("Received the PUT request to modify marks of a quiz");
		return ResponseEntity.ok(service.updateMarks(id, marks));
	}
}
