package com.wondersgroup;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

@SpringBootApplication
public class Base0100CommandLineRunnerApplication {

    public static void main(String[] args) {

        System.out.println("app init start ...");

        SpringApplication.run(Base0100CommandLineRunnerApplication.class, args);

        System.out.println("app init stop ...");

    }



    @Bean
    public CommandLineRunner commandLineRunner(){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                System.out.println("commandLineRunner init....");
            }
        };
    }

    @Bean
    @Order(1)
    public CommandLineRunner commandLineRunner1(){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                System.out.println("commandLineRunner init 1....");
            }
        };
    }

    @Bean
    @Order(2)
    public CommandLineRunner commandLineRunner2(){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                System.out.println("commandLineRunner init 2....");
            }
        };
    }

}
