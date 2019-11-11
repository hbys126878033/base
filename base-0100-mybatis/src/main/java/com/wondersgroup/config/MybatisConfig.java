package com.wondersgroup.config;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author cl
 * @create 2019-05-27 12:15
 * <p>
 * mybatis相关配置：
 * MapperScan 是mybatis自己的实现，可以换成Mapper的实现
 * <p>
 * <p>
 * mybatis的其他参数配置在application.properties文件中
 * <p>
 * 比如扫描的mapper的xml文件
 * 分页插件pagehelper的配置
 */
//@Configuration
@MapperScan(value = "com.wondersgroup.model.mapper", sqlSessionFactoryRef = "sqlSessionFactory", sqlSessionTemplateRef = "sqlSessionTemplate")
public class MybatisConfig {


    @Value("${mybatis.mapper-locations}")
    private String mapperPath;

    @Autowired
    @Qualifier("customDataSource")
    private DataSource customDataSource;


    /**
     * 方法功能：
     * 定制SqlSessionFactoryBean，不使用springBoot自动配置的数据源，、
     * 注意需要设置mybatis的配置文件到该Bean中，不然mybatis使用会报错
     * <p>
     * <p>
     * 结论：如果需要使用自定义配置，需要配置它所使用或者依赖的配置
     *
     * @return: org.mybatis.spring.SqlSessionFactoryBean
     * @Author: chenlin
     **/
    @Bean
    @Primary
    @ConditionalOnMissingBean  ////当容器里没有指定的bean的情况下创建bean
    public SqlSessionFactoryBean customSqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(customDataSource);
        //设置mybatis的主配置文件
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        if (!StringUtils.hasText(mapperPath)) {
            mapperPath = "classpath:/mapper/*Mapper.xml";
        }
        Resource[] resources = resolver.getResources(mapperPath);
        bean.setMapperLocations(resources);

        /*  configuration相关的示例配置
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(configuration);*/

        return bean;
    }

    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory customSqlSessionFactory() throws Exception {
        return customSqlSessionFactoryBean().getObject();
    }

    @Bean(name = "sqlSessionTemplate")
    @Primary
    public SqlSessionTemplate customSqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(customSqlSessionFactory());
    }
}
