package com.epam.HomeTask1Collections;

import java.util.Comparator;
import java.util.Map;

public class SortMapOfNumbersByValue implements Comparator<Map.Entry<Integer,Integer>>{
	public int compare(Map.Entry<Integer,Integer>entry1,Map.Entry<Integer, Integer>entry2) {
			return (-1*(entry1.getValue().compareTo(entry2.getValue())));
	}
}
