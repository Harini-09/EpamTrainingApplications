package com.epam.database;

import java.util.List;

import com.epam.customexceptions.InvalidQuestionIdEntryException;
import com.epam.customexceptions.InvalidQuizIdEntryException;
import com.epam.customexceptions.NoSuchQuestionExistInQuizException;
import com.epam.entities.Question;
import com.epam.entities.Quiz;

public interface QuizDao {
	public List<Quiz> viewQuiz();
	public boolean removeQuiz(int quizId);
	public Quiz createQuiz(Quiz newQuiz);
	public boolean addQuestion(int quizId, int questionId);
	public boolean removeQuestion(int quizId, int questionId);
	public int updateMarks(int quizId,int marks);
	public Question addNewQuestion(int questionId);
	public void isQuestionIdPresent(int questionId) throws InvalidQuestionIdEntryException;
	public void isQuizTitlePresent(int quizId) throws InvalidQuizIdEntryException;
	public void isQuestionInQuizPresent(int quizId, int questionId) throws NoSuchQuestionExistInQuizException;

}
