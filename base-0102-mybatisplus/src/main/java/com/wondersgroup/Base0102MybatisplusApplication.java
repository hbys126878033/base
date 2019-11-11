package com.wondersgroup;

import com.wondersgroup.model.mapper.SysUserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan(value = "com.wondersgroup.model.mapper")
public class Base0102MybatisplusApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Base0102MybatisplusApplication.class, args);

        SysUserMapper sysUserMapper = context.getBean(SysUserMapper.class);
        System.out.println(sysUserMapper);

        System.out.println(sysUserMapper.query());
    }

}
