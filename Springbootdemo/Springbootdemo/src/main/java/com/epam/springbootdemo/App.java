package com.epam.springbootdemo;









import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.epam.springbootdemo.ui.MainDashboard;








@SpringBootApplication
public class App 
{
	
	
	
    public static void main( String[] args )
    {
    	
    	@SuppressWarnings("resource")
	   ApplicationContext context=SpringApplication.run(App.class, args);
       MainDashboard mainDashboard=context.getBean(MainDashboard.class);
      


		
		
     	mainDashboard.userInterface();
     	
        
        
    }

}
