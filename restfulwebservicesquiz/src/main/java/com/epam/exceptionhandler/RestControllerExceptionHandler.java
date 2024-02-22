package com.epam.exceptionhandler;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.epam.customexceptions.InvalidQuestionIdEntryException;
import com.epam.customexceptions.InvalidQuizIdEntryException;
import com.epam.customexceptions.NoSuchQuestionExistInQuizException;
import com.epam.customexceptions.ProcessFailedException;

@RestControllerAdvice
public class RestControllerExceptionHandler {
	
	private final Logger logger = LogManager.getLogger(RestControllerExceptionHandler.class);
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	public ExceptionResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex,WebRequest req) {
		List<String> inputErrors = new ArrayList<>();
		ex.getAllErrors().forEach(err->inputErrors.add(err.getDefaultMessage()));
		logger.error("MethodArgumentNotValidException occured = {}",inputErrors);
		return new ExceptionResponse(new Date().toString(),HttpStatus.BAD_REQUEST.name(),inputErrors.toString(),req.getDescription(false));
	}
	
	@ExceptionHandler(InvalidQuestionIdEntryException.class)
	@ResponseStatus(value=HttpStatus.OK)
	public ExceptionResponse handleInvalidQuestionIdEntryException(InvalidQuestionIdEntryException ex,WebRequest req) {
		logger.error("InvalidQuestionIdEntryException occured = {}",ex.getMessage());
		return new ExceptionResponse(new Date().toString(),HttpStatus.OK.name(),ex.getMessage(),req.getDescription(false));
	}
	
	@ExceptionHandler(ProcessFailedException.class)
	@ResponseStatus(value=HttpStatus.OK)
	public ExceptionResponse handleProcessFailedException(ProcessFailedException ex,WebRequest req) {
		logger.error("ProcessFailedException occured = {}",ex.getMessage());
		return new ExceptionResponse(new Date().toString(),HttpStatus.OK.name(),ex.getMessage(),req.getDescription(false));
	} 
	
	@ExceptionHandler(InvalidQuizIdEntryException.class)
	@ResponseStatus(value=HttpStatus.OK)
	public ExceptionResponse handleInvalidQuizIdEntryException(InvalidQuizIdEntryException ex,WebRequest req) {
		logger.error("InvalidQuizIdEntryException occured = {}",ex.getMessage());
		return new ExceptionResponse(new Date().toString(),HttpStatus.OK.name(),ex.getMessage(),req.getDescription(false));
	}
	
	@ExceptionHandler(NoSuchQuestionExistInQuizException.class)
	@ResponseStatus(value=HttpStatus.OK)
	public ExceptionResponse handleNoSuchQuestionExistInQuizException(NoSuchQuestionExistInQuizException ex,WebRequest req) {
		logger.error("NoSuchQuestionExistInQuizException occured = {}",ex.getMessage());
		return new ExceptionResponse(new Date().toString(),HttpStatus.OK.name(),ex.getMessage(),req.getDescription(false));
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	public ExceptionResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException ex,WebRequest req) {
		logger.error("HttpMessageNotReadableException occured = {}",ex.getMessage());
		return new ExceptionResponse(new Date().toString(),HttpStatus.BAD_REQUEST.toString(),ex.getMessage(),req.getDescription(false));
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	public ExceptionResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex,WebRequest req) {
		logger.error("MethodArgumentTypeMismatchException occured = {}",ex.getMessage());
		return new ExceptionResponse(new Date().toString(),HttpStatus.BAD_REQUEST.toString(),ex.getMessage(),req.getDescription(false));
	}
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionResponse handleRuntimeException(RuntimeException ex,WebRequest req) {
		logger.error("RuntimeException occured = {}",ex.getMessage());
		return new ExceptionResponse(new Date().toString(),HttpStatus.INTERNAL_SERVER_ERROR.toString(),ex.getMessage(),req.getDescription(false));
	}
}
