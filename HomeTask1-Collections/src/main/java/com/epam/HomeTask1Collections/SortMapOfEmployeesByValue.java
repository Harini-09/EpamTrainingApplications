package com.epam.HomeTask1Collections;

import java.util.Comparator;
import java.util.Map;

public class SortMapOfEmployeesByValue implements Comparator<Map.Entry<Integer,String>> {
	public int compare(Map.Entry<Integer,String> entry1,Map.Entry<Integer,String>entry2) {
		return (-1*(entry1.getValue().compareTo(entry2.getValue())));
	}
}
