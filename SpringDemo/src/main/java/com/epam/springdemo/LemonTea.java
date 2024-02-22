package com.epam.springdemo;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class LemonTea implements CoffeeType{
	public String getCoffee() {
		return "Lemon Tea";
	}

}
