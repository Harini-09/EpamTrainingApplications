package com.epam.entities;

import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
@Table(name="user")
public class User{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int userId;
	String userName;
	String email;
	
//	@OneToOne
//	@JoinColumn(name="address_Id")
//	Address address;
	
//	@OneToMany(mappedBy="user")
//	@JoinTable(name="user_addresses",joinColumns=@JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="address_id"))
//	List<Address> addresses = new ArrayList<>();
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name="users_addresses",joinColumns=@JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="address_id"))
	List<Address> addresses = new ArrayList<>();
	
	
	public User(String userName, String email) {
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


	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
		this.addresses.forEach(address->{
			address.getUsers().add(this);
			address.setUsers(address.getUsers());
		});
	}

	

	
	
	
	

}




//public class User {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	int userId;
//	String userName;
//	String email;
//	
//	@ElementCollection(targetClass=Address.class)
//	List<Address> addresses = new ArrayList<>();
//
//	public User(String userName, String email) {
//		super();
//		this.userName = userName;
//		this.email = email;
//	}
//	
	
//	
//	@ManyToMany(mappedBy="usersList",cascade=CascadeType.REMOVE)
//	List<Address> addresses = new ArrayList<>();
//	
////	@ManyToMany(mappedBy="usersList",cascade = CascadeType.REMOVE)
////	List<Address> addresses = new ArrayList<>();
//
//	public User(String userName, String email) {
//	super();
//	this.userName = userName;
//	this.email = email;
//}

	

	
	
	


