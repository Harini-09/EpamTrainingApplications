package com.epam.HomeTask1Collections;

import java.util.Comparator;

public class NameComparator implements Comparator<Employee>{
	
	public int compare(Employee employee1,Employee employee2)
	{
		return employee1.getName().compareTo(employee2.getName());
	}

}
