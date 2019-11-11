package com.wondersgroup.base0300aop;

import com.wondersgroup.base0300aop.aop.Aop4Log;
import com.wondersgroup.base0300aop.services.HelloService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Base0300AopApplication {


    public static void main(String[] args) {
        SpringApplication.run(Base0300AopApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(HelloService helloService, ApplicationContext applicationContext) {
        return (args) -> {
            Aop4Log logAop = applicationContext.getBean(Aop4Log.class);
            System.out.println(logAop);
            helloService.say();
        };
    }

}
