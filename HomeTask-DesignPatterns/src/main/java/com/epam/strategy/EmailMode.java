package com.epam.strategy;

public class EmailMode implements ShareMode {

	@Override
	public void share() {
		System.out.println("Sharing through Email");		
	}

}
