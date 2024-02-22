package com.epam.springdemo;

import org.springframework.stereotype.Component;

@Component
public class Cappaccino implements CoffeeType{
	public String getCoffee() {
		return "Cappaccino";
	}
}
