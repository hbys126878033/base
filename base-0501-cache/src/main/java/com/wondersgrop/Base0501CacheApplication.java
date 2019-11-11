package com.wondersgrop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching//开启缓存
public class Base0501CacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(Base0501CacheApplication.class, args);

        System.out.println(System.getProperty("java.io.tmpdir"));
    }

}
