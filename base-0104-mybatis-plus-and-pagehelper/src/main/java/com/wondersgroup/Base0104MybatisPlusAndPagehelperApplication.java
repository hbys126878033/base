package com.wondersgroup;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan(value="com.wondersgroup.mapper")
public class Base0104MybatisPlusAndPagehelperApplication {

    public static void main(String[] args) {
        SpringApplication.run(Base0104MybatisPlusAndPagehelperApplication.class, args);
    }



    @Bean
    public CommandLineRunner commandLineRunner(ConfigurableApplicationContext context){

        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                String[] beanDefinitionNames = context.getBeanDefinitionNames();
                for (String name : beanDefinitionNames) {
                    System.out.println(name);
                }
            }
        };
    }


}
