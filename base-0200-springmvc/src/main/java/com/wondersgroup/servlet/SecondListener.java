package com.wondersgroup.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author chenlin
 * @create 2019-05-30 14:42
 * @description: TODO
 * @version：1.0
 **/

public class SecondListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("SecondListener init");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
