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

@WebListener
public class FirstListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("FirstListener init");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
