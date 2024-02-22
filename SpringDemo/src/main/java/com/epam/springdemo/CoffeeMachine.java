package com.epam.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component(value="machine")
public class CoffeeMachine {
	
	@Autowired
	@Qualifier("cappaccino")
	CoffeeType coffeeType;

	public CoffeeType getCoffeeType() {
		return coffeeType;
	}

//	public void setCoffeeType(CoffeeType coffeeType) {
//		this.coffeeType = coffeeType;
//	}
	
	public void orderCoffee() {
		System.out.println("Ordered coffee is : "+coffeeType.getCoffee());
	}

}
