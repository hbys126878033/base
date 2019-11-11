package com.wondersgroup;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;


/**
 * 此处是配置不需要自动的的配置类
 */
//@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
public class Base0100MybatisApplication {


    private static final Logger logger = LoggerFactory.getLogger(Base0100MybatisApplication.class);


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Base0100MybatisApplication.class, args);

        logger.info("-----------------------------程序加载成功-----------------------------");
        System.out.println(context);
        String[] names = context.getBeanDefinitionNames();
        for (int i = 0, length = names.length; i < length; i++) {
            System.out.println(names[i]);
        }

        /*DruidDataSource d = context.getBean(DruidDataSource.class);
        System.out.println(d.getMaxActive());
        System.out.println(d.isTestOnReturn());
        System.out.println(d);

        DruidDataSource customDataSource = (DruidDataSource)context.getBean("customDataSource");
        System.out.println(customDataSource.getMaxActive());
        System.out.println(customDataSource.isTestOnReturn());
        System.out.println(customDataSource);*/
    }


    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }

}
