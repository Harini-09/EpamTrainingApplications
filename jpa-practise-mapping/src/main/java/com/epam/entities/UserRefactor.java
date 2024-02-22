package com.epam.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class UserRefactor {

	int userId;
	String userName;
	String email;
	
	@OneToMany
	List<AddressDemo> addressList = new ArrayList<AddressDemo>();

	public UserRefactor(String userName, String email) {
		super();
		this.userName = userName;
		this.email = email;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddressList(List<AddressDemo> addressList) {
		addressList.forEach(address->address.setUser(this));
		this.addressList = addressList;
	}

	
	

	
	
	
	
	
	
}	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	

	
/*
//	@OneToOne(mappedBy="user",cascade = CascadeType.REMOVE)
//	@JsonIgnore	 //you may want to ignore some fields that contain sensitive or irrelevant information to serialize(Converting the Java object to JSON) and deserialize
//	Address address;
	
	
	//@JoinTable(name="user-addresses",joinColumns = @JoinColumn(name="userId"),inverseJoinColumns = @JoinColumn(name="addressId"))

//	@OneToMany(mappedBy="user",cascade=CascadeType.REMOVE)
//	List<Address> addressList;
	
	@ManyToMany(mappedBy="users",cascade=CascadeType.REMOVE)
	List<Address> addressList=new ArrayList();


//	@Override
//	public String toString() {
//		return "User [userId=" + userId + ", userName=" + userName + ", email=" + email + "]";
//	}
	
	*/
	
	

