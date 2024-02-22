package com.epam.entities;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Data
@NoArgsConstructor
@Entity
@Table(name="address")
@Embeddable
public class Address{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int addressId;
	int flatNo;
	String apartmentName;
	String city; 
	
//	@OneToOne
//	User user;
	
//	@ManyToOne
//	@JoinColumn(name="user_id")
//	User user;
	
	@ManyToMany(mappedBy="addresses")
	List<User> users = new ArrayList<>();

	public Address(int flatNo, String apartmentName, String city) {
		super();
		this.flatNo = flatNo;
		this.apartmentName = apartmentName;
		this.city = city;
	}
	
	
	
	

}













//@Getter
//@ToString
//@EqualsAndHashCode
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//public class Address {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	int addressId;
//	int flatNo;
//	String apartmentName;
//	String city;
//	
//	@ManyToMany
//	List<User> usersList = new ArrayList<>();
//	
////	@ManyToMany
////	List<User> usersList=new ArrayList<>();
//
//	public void setAddressId(int addressId) {
//		this.addressId = addressId;
//	}
//
//	public void setFlatNo(int flatNo) {
//		this.flatNo = flatNo;
//	}
//
//	public void setApartmentName(String apartmentName) {
//		this.apartmentName = apartmentName;
//	}
//
//	public void setCity(String city) {
//		this.city = city;
//	}
//
//	public void setUsersList(List<User> usersList) {
//		for(User user:usersList) {
//			user.getAddresses().add(this);
//		}
//		this.usersList = usersList;
//	}
//
//	
//
//	
//	
//}
