package com.epam;

public class User {
	private int userId;
	private static String userName;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public static String getUserName() {
		return userName;
	}

	public static void setUserName(String userName) {
		User.userName = userName;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + userId;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
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
		if (userId != other.userId)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	

//	public Address getAddress() {
//		//userId = addressId;
//		return new Address();
//	}
//	
}

//class Address extends User {
//		private int addressId;
//		private String apartmentName;
//		private int flatNo=101;
//		public int getAddressId() {
//			return addressId;
//		}
//		public String getApartmentName() {
//			return apartmentName;
//		}
//		public int getFlatNo() {
//			return flatNo;
//		}
//}


