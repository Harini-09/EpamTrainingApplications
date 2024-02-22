package PractiseGenerics;


public class Main {
	
	public static void main(String[] args) {
		GenericUtility.receiveLeastValue(10, 12); // Least is 10 - it will be printed in console
		/*
		 * Like above code it has to work for Double, Float, Long as well as custom objects
		 * in our case like Customer( it has to be accepted as parameter) to the generic
		 * method.
		 * 
		 * Commenting the below code as it will work once you implement the generic method for 
		 * receiveLeastValue() method. You have to execute the below 3 commented lines code 
		 * without any issue.
		 * 
		 */
		@SuppressWarnings("unused")
		int a;
		System.out.println(GenericUtility.receiveLeastValue(10.9, 45.8)); // Output must be 10.9
		System.out.println(GenericUtility.receiveLeastValue(28.9f, 12.8f)); // Output must be  12.8
		System.out.println(GenericUtility.receiveLeastValue(new Customer("Adam John", 65000.90), new Customer("Steve Rolca", 40000.0))); //Output must be Customer [customerName=Steve Rolca, customerSalary=40000.0]
	}
}
