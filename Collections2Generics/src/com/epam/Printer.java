package com.epam;

public class Printer<T extends Animal> {
	T thingToPrint;
	//V otherThing;

	public Printer(T thingToPrint) {
		this.thingToPrint = thingToPrint;
		//this.otherThing = otherThing;
	}

	public void print() {
		thingToPrint.eat();
		System.out.println(thingToPrint);
	}
	
	
}
