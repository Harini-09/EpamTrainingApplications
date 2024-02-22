package com.epam.restcontroller;

import java.util.List;

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
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/quizes")
@Slf4j
public class QuizRestController {

	@Autowired
	QuizLibraryService service;
	
	@PostMapping(value="/save",produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<QuizDto> saveQuiz(@RequestBody @Valid QuizDto quizDto) {
		log.info("Received the POST request to save a question");
		return new ResponseEntity<>(service.insert(quizDto), HttpStatus.CREATED);
	}

	@GetMapping(value="/view",produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Quiz>> getQuizes() {
		log.info("Received the GET request to retrieve all the quizzes");
		return new ResponseEntity<>(service.view(), HttpStatus.OK);
	}

	@DeleteMapping(value="delete/{quiz_id}",produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> deleteQuiz(@PathVariable("quiz_id") int id) throws InvalidQuizIdEntryException {
		log.info("Received the DELETE request to delete a quiz");
		return new ResponseEntity<>(service.delete(id),HttpStatus.NO_CONTENT);
	}

	@PutMapping(value="questionaddition/{question_id}",produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> addQuizQuestion(@PathVariable("question_id") int id, @RequestParam int quizid) throws InvalidQuestionIdEntryException, InvalidQuizIdEntryException {
		log.info("Received the PUT request to add a question to a quiz");
		return ResponseEntity.ok(service.addQuestion(quizid, id));
	}

	@PutMapping(value="questiondeletion/{question_id}",produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> deleteQuizQuestion(@PathVariable("question_id") int id, @RequestParam int quizid) throws InvalidQuizIdEntryException, NoSuchQuestionExistInQuizException  {
		log.info("Received the PUT request to delete a question from a quiz");
		return ResponseEntity.ok(service.removeQuestion(quizid, id));
	}

	@PutMapping(value="marksupdation/{quiz_id}",produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> updateTotalMarks(@PathVariable("quiz_id") int id, @RequestParam int marks) throws InvalidQuizIdEntryException  {
		log.info("Received the PUT request to modify marks of a quiz");
		return ResponseEntity.ok(service.updateMarks(id, marks));
	}
}
