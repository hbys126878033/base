package com.wondersgroup;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class Base0200SpringMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(Base0200SpringMvcApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext context) {

        return (args) -> {
            String[] names = context.getBeanDefinitionNames();
            Arrays.sort(names);
            for (String name : names) {
                System.out.println(name);
            }
        };
    }
}
