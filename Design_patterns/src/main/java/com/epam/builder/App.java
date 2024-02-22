package com.epam.builder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class App {
	

	public static void main(String[] args) {
		
		Bicycle bicycle = Bicycle.BicycleBuilder.builder()
				.gearsNeeded(true)
				.whichColor("Olive Green")
				.doubleStandNeeded(false)
				.doubleSeatNeeded(false)
				.bottleHolderNeeded(true)
				.mobileHolderNeeded(true)
				.helmetNeeded(true)
				.carrier(false)
				.build();
		
		System.out.println(bicycle);
	
	}
}
