package com.epam.singleton;

public class App {
	public static void main(String[] args) {
		CandyMaker candymaker = CandyMaker.getGlobalInstance();
		candymaker.fill();
		candymaker.boil();
		candymaker.drain();
		System.out.println(candymaker);

	}
}
