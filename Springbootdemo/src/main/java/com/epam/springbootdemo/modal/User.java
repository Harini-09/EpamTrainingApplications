package com.epam.springbootdemo.modal;

import java.util.Objects;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="user")
@Entity
public class User {
   	@Id
	private int userID;
   	
	 private String userName;
	 private String userType;
	 private String password;
	 private String emailID;
	 
	public User() {
		
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public User(int userID, String userName, String userType, String password, String emailID) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.userType = userType;
		this.password = password;
		this.emailID = emailID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(emailID, password, userID, userName, userType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(emailID, other.emailID) && Objects.equals(password, other.password) && userID == other.userID
				&& Objects.equals(userName, other.userName) && Objects.equals(userType, other.userType);
	}

}
