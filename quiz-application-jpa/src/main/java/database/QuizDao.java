package database;

import java.util.List;

import customexceptions.InvalidQuestionIdEntryException;
import customexceptions.InvalidQuizTitleEntryException;
import customexceptions.NoSuchQuestionExistInQuizException;
import model.Question;
import model.Quiz;

public interface QuizDao {
	public List<Quiz> viewQuiz();
	public boolean removeQuiz(int quizId);
	public boolean createQuiz(int quizid, Quiz newQuiz);
	public boolean addQuestion(int quizId, String questionId);
	public boolean removeQuestion(int quizId, String questionId);
	public int updateMarks(int quizId,int marks);
	public Question addNewQuestion(String questionId);
	public void isQuestionIdPresent(String questionId) throws InvalidQuestionIdEntryException;
	public void isQuizTitlePresent(int quizId) throws InvalidQuizTitleEntryException;
	public void isQuestionInQuizPresent(int quizId, String questionId) throws NoSuchQuestionExistInQuizException;

}
