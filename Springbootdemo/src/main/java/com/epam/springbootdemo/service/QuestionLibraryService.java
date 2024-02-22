package com.epam.springbootdemo.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.springbootdemo.database.DatabaseOperations;
import com.epam.springbootdemo.modal.Question;

@Service
public class QuestionLibraryService {
	@Autowired
	private DatabaseOperations questionDAO;



	public Object modifyQuestion(Object updatedvalue, int questionID,int choice) {
	
		return questionDAO.updateData(updatedvalue, questionID, choice);
	}

	public Object viewList(int questionID) {	
		
		return questionDAO.viewData(questionID);
	}
	public Object getAlldata() {	
		
		return questionDAO.getAllData();
	}

	public boolean deleteQuestion(int questionID) {
		
		return questionDAO.deleteData(questionID);

	}

	public  Object addQuestion(Question newQuestion) {
		
		return questionDAO.addData(newQuestion);

	}

}
