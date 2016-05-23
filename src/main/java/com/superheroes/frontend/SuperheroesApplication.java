package com.superheroes.frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SuperheroesApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SuperheroesApplication.class, args);
		String[] beanNames = ctx.getBeanDefinitionNames();
		/*Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			System.out.println(beanName);
		}*/
	}
}
