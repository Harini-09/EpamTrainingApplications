package com.epam.springbootdemo.database;


public interface QuizDataBaseOperations extends DatabaseOperations {
	public Object addQuestions(int questionID,int quizID);
	public Object removeQuestions(int questionID, int quizID);

}
