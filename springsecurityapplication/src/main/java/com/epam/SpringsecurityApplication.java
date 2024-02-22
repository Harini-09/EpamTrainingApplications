package com.epam;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.epam.dtoconverter.QuestionDtoConverter;
import com.epam.dtoconverter.QuizDtoConverter;
import com.epam.dtoconverter.UserDtoConverter;
import com.epam.entities.User;
import com.epam.repository.UserRepo;

@SpringBootApplication
public class SpringsecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringsecurityApplication.class, args);
	}
	
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public QuestionDtoConverter getQuestionDtoConverter() {
		return new QuestionDtoConverter();
	}
	
	@Bean
	public QuizDtoConverter getQuizDtoConverter() {
		return new QuizDtoConverter();
	}
	
	@Bean
	public UserDtoConverter getUserDtoConverter() {
		return new UserDtoConverter();
	}
	
	@Bean
	CommandLineRunner commandLineRunner(UserRepo users, PasswordEncoder encoder){
	   return args -> {
	      users.save(new User("user@epam.com", encoder.encode("User@123"),"ROLE_USER" ));
	      users.save(new User("admin@epam.com", encoder.encode("Admin@9016"),"ROLE_USER,ROLE_ADMIN" ));
//		   users.save(new User("admin3@epam.com",encoder.encode("Admin3@123"),"ROLE_USER,ROLE_ADMIN"));
	   };
	}
	
	@Bean
	PasswordEncoder passwordEncoder(){
	return new BCryptPasswordEncoder();
	}

}
