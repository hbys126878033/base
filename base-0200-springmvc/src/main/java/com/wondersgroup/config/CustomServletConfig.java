package com.wondersgroup.config;

import com.wondersgroup.servlet.SecondFilter;
import com.wondersgroup.servlet.SecondListener;
import com.wondersgroup.servlet.SecondServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenlin
 * @create 2019-05-30 12:43
 * @description: 配置servlet中的servlet ,filter,listener的方式
 * 相关的配置均提供了多种方式，以servlet为例
 * 1.使用@WebServlet注解标记servlet,结合@ServletComponentScan让SpringBoot扫描到该注解
 * 2.使用ServletRegistrationBean即可
 * @version：1.0
 **/
@Configuration
@ServletComponentScan("com.wondersgroup.servlet")
public class CustomServletConfig {

    /**
     * 方法作用：注册servlet
     *
     * @param
     * @return: org.springframework.boot.web.servlet.ServletRegistrationBean
     * @createDate: 2019/5/30 14:45
     * @createAuthor: chenlin
     * @updateDate: 2019/5/30 14:45
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {

        ServletRegistrationBean bean = new ServletRegistrationBean(new SecondServlet());
        //bean.setServlet();
        bean.addUrlMappings("/second");
        return bean;
    }

    /**
     * 方法作用：注册filter
     *
     * @param
     * @return: org.springframework.boot.web.servlet.FilterRegistrationBean
     * @createDate: 2019/5/30 14:45
     * @createAuthor: chenlin
     * @updateDate: 2019/5/30 14:45
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {

        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new SecondFilter());
        bean.addUrlPatterns("/*");
        bean.setOrder(1);
        return bean;
    }


    /**
     * 方法作用：注册listener
     *
     * @param
     * @return: org.springframework.boot.web.servlet.ServletListenerRegistrationBean
     * @createDate: 2019/5/30 14:45
     * @createAuthor: chenlin
     * @updateDate: 2019/5/30 14:45
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean() {

        ServletListenerRegistrationBean bean = new ServletListenerRegistrationBean();
        bean.setListener(new SecondListener());
        return bean;
    }
}
