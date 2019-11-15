package com.wondersgroup;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author chenlin
 * @create 2019-11-15 9:12
 * @description: TODO
 * @version：1.0
 **/
@Component
public class ApplicationContextListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(null == contextRefreshedEvent.getApplicationContext().getParent()) {
            System.out.println("IOC init over 。。。");
            /**
             * 此时可以初始化自己管理的Bean
             */

        }
    }
}
