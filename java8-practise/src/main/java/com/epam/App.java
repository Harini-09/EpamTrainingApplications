package com.epam;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String arg[]) {
//		User user = new User() {
//			private int addressId;
//			private String apartmentName;
//			private int flatNo=101;
//			private int userId;
//
//			public int getUserId() {
//				System.out.println("HI");
//				return 101;
//			}
//
//			public void setUserId(int userId) {
//				this.userId = userId;
//			}
//			public int getAddressId() {
//				return addressId;
//			}
//			public String getApartmentName() {
//				return apartmentName;
//			}
//			public int getFlatNo() {
//				return flatNo;
//			}
//		};
//		
//	int res = user.getUserId();
//	System.out.println(res);
		
//		Faculty faculty = new FacultyImpl();
//		faculty.verifySalaryDetails();
//		faculty.doBgVerification();
		//FunctionalInterfaces  Lambdas  StreamAPI
		Faculty faculty = new FacultyImpl();			
		
		faculty.doBgVerification("Sid",5);
	} 
}
class FacultyImpl implements Faculty{

	@Override
	public void doBgVerification(String name, int exp) {
		System.out.println(name+" "+exp);
		
	}

}



