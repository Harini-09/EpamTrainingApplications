package PractiseGenerics;

public class GenericUtility {
	
	//Non-Generic Method
	/*
	 * Convert this non-generic method to a generic method so that it can
	 * compare String, Double, Float and any custom class object like 
	 * in our case i.e Customer
	 */
	public static <T extends Comparable<T>> T receiveLeastValue(T object1, T object2) {
		int a;
		if(object1.compareTo(object2)<1) 
			return object1;
			else if(object1.compareTo(object2)>1)
				return object2;
			else
				return object1;
	}
	
	
	
	
	 
	

}

