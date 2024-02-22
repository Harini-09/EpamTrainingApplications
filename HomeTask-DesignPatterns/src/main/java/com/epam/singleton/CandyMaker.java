package com.epam.singleton;

public class CandyMaker {
	private static CandyMaker candymaker;
	private boolean empty;
	private boolean boiled;

	// This code is only started when the boiler is empty
	public CandyMaker() {
		empty = true;
		boiled = false;
	}

	public static CandyMaker getGlobalInstance() {
		if (candymaker == null) {
			candymaker = new CandyMaker();
		}
		return candymaker;
	}

	// to fill the boiler it must be empty, and once its full,
	// we set the empty and boiled flags
	public void fill() {
		if (isEmpty()) { // isEmpty() always returns the status of empty
			empty = false;
			boiled = false;
			// fill the candyMaker with milk and chocolate mix
		}
	}

	// To drain the boiler, it must be full(non-empty) and also boiled.
	// once it is drained, we set the empty back to true
	public void drain() {
		if (!isEmpty() && isBoiled()) {
			// drain the boiled milk and chocolate
			empty = true;
		}
	}

	// to boil the mixture the boiler has to be full and not already boiled.
	// once its boiled we set the boiled flag to true
	public void boil() {
		if (!isEmpty() && !isBoiled()) {
			// bring the content to boil
			boiled = true;
		}
	}

	public boolean isEmpty() {
		return empty;
	}

	public boolean isBoiled() {
		return boiled;
	}

	@Override
	public String toString() {
		return "CandyMaker [empty=" + empty + ", boiled=" + boiled + "]";
	}

}
