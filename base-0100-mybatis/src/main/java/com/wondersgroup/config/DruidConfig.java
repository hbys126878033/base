package com.wondersgroup.config;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;

import javax.sql.DataSource;

/**
 * @author cl
 * @create 2019-05-24 22:34
 */

//@Configuration
public class DruidConfig {



    /* @Bean
    访问 http://localhost:8080/druid druid管理页面的配置信息
    public ServletRegistrationBean statViewServle(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        //白名单：
        servletRegistrationBean.addInitParameter("allow","192.168.1.218,127.0.0.1");
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的即提示:Sorry, you are not permitted to view this page.
        servletRegistrationBean.addInitParameter("deny","192.168.1.100");
        //登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername","druid");
        servletRegistrationBean.addInitParameter("loginPassword","12345678");
        //是否能够重置数据.
        servletRegistrationBean.addInitParameter("resetEnable","false");
        return servletRegistrationBean;
    }*/

  /*  @Bean
    public FilterRegistrationBean statFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }*/

    @Bean
    PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }


    /*****
     * 方法功能：配置数据库的基本链接信息,SpringBoot默认会注入数据源，
     *         此时是自定义数据源，并且项目中优先使用该数据源，因为用Primary注解修饰了
     *         因为是优先使用自定义数据源，所有mybatis使用的SqlSessionFactoryBean也需要自定义，
     *         不能使用mybatis的starter来初始化，故SqlSessionFactory和SqlSessionTemplate，需要自定义，并且优先使用
     * @Date:
     * @params:
     * @return:
     * @Author: chenlin
     **/
    @Bean(name = "customDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")    //可以在application.properties中直接导入
    public DataSource dataSource() {
        DruidDataSource dataSource = DataSourceBuilder.create().type(DruidDataSource.class).build();

        dataSource.setMaxActive(100);
        dataSource.setTestOnReturn(true);

        System.out.println(dataSource);
        System.out.println(dataSource.getMaxActive());
        System.out.println(dataSource.isTestOnReturn());
        return dataSource;
    }

}
