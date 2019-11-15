package com.wondersgroup.base0106dynamicdatasource.dynamic;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenlin
 * @create 2019-11-15 10:43
 * @description: TODO
 * @version：1.0
 **/

@Configuration
public class DataSourceConfig {

    @Bean
    public DynamicDataSource dataSource(){
        DynamicDataSource dataSource = new DynamicDataSource();

        Map<Object,Object> map = new HashMap<Object,Object>();

        javax.sql.DataSource db1 = DataSourceBuilder.create().build();
        javax.sql.DataSource db2 = DataSourceBuilder.create().build();

        map.put("db1",db1);
        map.put("db2",db2);
        dataSource.setTargetDataSources(map);
        dataSource.setDefaultTargetDataSource("db1");
        return dataSource;
    }
}
