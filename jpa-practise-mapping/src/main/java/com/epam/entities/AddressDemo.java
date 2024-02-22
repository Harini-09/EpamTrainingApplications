package com.epam.entities;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressDemo {

	int addressId;
	int flatNo;
	String apartmentName;
	String city;
	
	@ManyToOne
	UserRefactor user;
	
	public AddressDemo(int flatNo, String apartmentName, String city) {
		super();
		this.flatNo = flatNo;
		this.apartmentName = apartmentName;
		this.city = city;
	}
	
	

}	
	
	
	
	
	
	












	
	
	
	
	
	
/*
//	@OneToOne
//	User user;
	
//	@ManyToOne
//	User user;
	
	@ManyToMany
	List<User> users;
	
	public Address(int flatNo, String apartmentName, String city) {
		super();
		this.flatNo = flatNo;
		this.apartmentName = apartmentName;
		this.city = city;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public void setFlatNo(int flatNo) {
		this.flatNo = flatNo;
	}
	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}
	public void setCity(String city) {
		this.city = city;
	}
//	public void setUser(User user) {
//		user.setAddress(this);
//		this.user = user;
//	}
	
	public void setUsers(List<User> users) {
		for(User user:users) {
			user.getAddressList().add(this);
		}
		this.users=users;
	}
	
	
}*/
