package com.wondersgroup;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author chenlin
 * @create 2019-11-15 9:36
 * @description: TODO
 * @version：1.0
 **/

@Component
@Slf4j
public class StartedUpRunner implements ApplicationRunner {

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Value("${spring.application.name:AAA}")
    private String applicationName;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (this.applicationContext.isActive()){
            log.info("{} 启动完成",this.applicationName);
            System.out.println(this.applicationName+"启动完成");
        }
    }
}
