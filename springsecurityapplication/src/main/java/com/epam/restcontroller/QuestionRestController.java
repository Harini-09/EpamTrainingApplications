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
import com.epam.customexceptions.ProcessFailedException;
import com.epam.dtos.QuestionDto;
import com.epam.entities.Question;
import com.epam.service.QuestionLibraryService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/questions")
public class QuestionRestController {

	@Autowired 
	QuestionLibraryService service;
	
	private final Logger logger = LogManager.getLogger(QuestionRestController.class);
		
	@GetMapping("/view")
	public ResponseEntity<List<Question>> getQuestions() {
		logger.info("Received the GET request to retrieve all the questions");
		return new ResponseEntity<>(service.viewQuestions(),HttpStatus.OK);
	} 
	
	@PostMapping(value="/save",produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<QuestionDto> saveQuestion(@RequestBody @Valid QuestionDto questionDto) {
		logger.info("Received the POST request to save a question");
		return new ResponseEntity<>(service.insert(questionDto),HttpStatus.CREATED);
	}
	
	@DeleteMapping(value="/delete/{question_id}",produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }) 
	public ResponseEntity<Object> deleteQuestion(@PathVariable("question_id") int id) throws InvalidQuestionIdEntryException, ProcessFailedException  {
		logger.info("Received the DELETE request to delete a question");
		return new ResponseEntity<>(service.delete(id),HttpStatus.NO_CONTENT);
	} 
	 
	@PutMapping(value="/title/{question_id}", produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> updateQuestionTitle(@PathVariable("question_id") int id, @RequestParam String title) throws InvalidQuestionIdEntryException {
		logger.info("Received the PUT request to modify the title of a question");
		return ResponseEntity.ok(service.modifyTitle(id, title));
	}
	
	@PutMapping(value="/questiondesc/{question_id}",produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> updateQuestionDescription(@PathVariable("question_id") int id, @RequestParam String question) throws InvalidQuestionIdEntryException  {
		logger.info("Received the PUT request to modify the question description of a question");
		return ResponseEntity.ok(service.modifyQuestion(id, question));
	}
	
	@PutMapping(value="/level/{question_id}", produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> updateQuestionLevel(@PathVariable("question_id") int id, @RequestParam String questionlevel) throws InvalidQuestionIdEntryException  {
		logger.info("Received the PUT request to modify the Question level of a question");
		return ResponseEntity.ok(service.modifyQuestionLevel(id, questionlevel));
	}
	
	@PutMapping(value="/options/{question_id}",produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> updateOptions(@PathVariable("question_id") int id, @RequestParam List<String> options) throws InvalidQuestionIdEntryException {
		logger.info("Received the PUT request to modify the options of a question");
		return ResponseEntity.ok(service.modifyOptions(id, options));
	}
	
	@PutMapping(value="/topictag/{question_id}",produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> updateTopicTag(@PathVariable("question_id") int id, @RequestParam String topictag) throws InvalidQuestionIdEntryException  {
		logger.info("Received the PUT request to modify the topic tag of a question");
		return ResponseEntity.ok(service.modifyTopicTag(id, topictag));
	}
	
	@PutMapping(value="/answer/{question_id}",produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> updateAnswer(@PathVariable("question_id") int id, @RequestParam String answer) throws InvalidQuestionIdEntryException  {
		logger.info("Received the PUT request to modify the answer of a question");
		return ResponseEntity.ok(service.modifyAnswer(id, answer));
	}
	
	@GetMapping(value="/question/{question_id}", produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }) 
	public ResponseEntity<Object> getTheQuestionById(@PathVariable("question_id") int id) throws InvalidQuestionIdEntryException  {
		logger.info("Received the GET request to retrieve question by id");
		return ResponseEntity.ok(service.getQuestionById(id));
	}	
}
