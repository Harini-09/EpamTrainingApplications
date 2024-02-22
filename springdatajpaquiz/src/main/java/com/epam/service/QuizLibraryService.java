package com.epam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.customexceptions.InvalidQuestionIdEntryException;
import com.epam.customexceptions.InvalidQuizIdEntryException;
import com.epam.customexceptions.NoSuchQuestionExistInQuizException;
import com.epam.entities.Question;
import com.epam.entities.Quiz;
import com.epam.repository.QuestionRepo;
import com.epam.repository.QuizRepo;

@Service
public class QuizLibraryService {

	@Autowired
	QuizRepo quizRepo;

	@Autowired
	QuestionRepo questionRepo;
	
	@Autowired
	private Validation validation;

	private Optional<Quiz> quiz;

	private Optional<Question> question;

	public Quiz insert(Quiz newQuiz) {
		return quizRepo.save(newQuiz);
	} 

	public boolean addQuestion(int quizId, int questionId) {
		quiz = quizRepo.findById(quizId);
		question = questionRepo.findById(questionId);
		List<Question> questions = validation.getQuestionsInQuiz(quiz.get());
		boolean isQuestionAdded = questions.add(question.get());
		quizRepo.save(quiz.get());
		return isQuestionAdded;
	}

	public boolean removeQuestion(int quizId, int questionId) {
		quiz = quizRepo.findById(quizId);
		question = questionRepo.findById(questionId);
		List<Question> questions = validation.getQuestionsInQuiz(quiz.get());
		boolean isPresent = questions.remove(question.get());
		quizRepo.save(quiz.get());
		return isPresent;
	}
 
	public int updateMarks(int quizId, int newmarks) {
		quiz = quizRepo.findById(quizId);
		quiz.get().setTotalMarks(newmarks);
		quizRepo.save(quiz.get());
		return quiz.get().getTotalMarks();
	} 
 
	public boolean delete(int quizId) {
		quizRepo.deleteById(quizId);
		return quizRepo.existsById(quizId);
		
	}

	public List<Quiz> view() {
		return (List<Quiz>) quizRepo.findAll();
	}

	public Question addNewQuestion(int questionId) {
		question = questionRepo.findById(questionId);
		return question.get();
	}

	public void isQuestionIdPresent(int questionId) throws InvalidQuestionIdEntryException {
		if (!questionRepo.existsById(questionId)) {
			throw new InvalidQuestionIdEntryException();
		} 
	}

	public void isQuizTitlePresent(int quizId) throws InvalidQuizIdEntryException {
		if (!quizRepo.existsById(quizId)) {
			throw new InvalidQuizIdEntryException();
		}
	}
 
	public void isQuestionInQuizPresent(int quizId, int questionId) throws NoSuchQuestionExistInQuizException {
		quiz = quizRepo.findById(quizId);
		question = questionRepo.findById(questionId);
		List<Question> questions = validation.getQuestionsInQuiz(quiz.get());
		if (!questions.contains(question.get())) {
			throw new NoSuchQuestionExistInQuizException();
		}
	}

}
