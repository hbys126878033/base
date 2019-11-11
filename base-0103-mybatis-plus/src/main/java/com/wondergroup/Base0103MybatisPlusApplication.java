package com.wondergroup;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value="com.wondergroup.mapper")
public class Base0103MybatisPlusApplication {


    public static void main(String[] args) {
        SpringApplication.run(Base0103MybatisPlusApplication.class, args);
    }

}
