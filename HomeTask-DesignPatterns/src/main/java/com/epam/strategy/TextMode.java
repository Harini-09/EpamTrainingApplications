package com.epam.strategy;

public class TextMode implements ShareMode {

	@Override
	public void share() {
		System.out.println("Sharing through Text Mode");
	}

}
