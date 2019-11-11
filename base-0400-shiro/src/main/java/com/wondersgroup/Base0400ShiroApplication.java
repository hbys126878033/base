package com.wondersgroup;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wondersgroup.model.mapper")
public class Base0400ShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(Base0400ShiroApplication.class, args);
    }

}
