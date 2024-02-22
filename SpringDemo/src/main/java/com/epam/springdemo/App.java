package com.epam.springdemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages={"com.epam.springdemo"})
public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
    	CoffeeMachine machine=context.getBean(CoffeeMachine.class);
    	  
    	for(String bean:context.getBeanDefinitionNames()) {
    		System.out.println(bean);
    	}
    	
   
    	
    	//CoffeeMachine machine = new CoffeeMachine();
    	//machine.setCoffeeType(new LemonTea());
    	machine.orderCoffee();
    }
    
//    @Bean
//    public CoffeeMachine getCoffeeMachine() {
//    	return new CoffeeMachine();
//    }
}
