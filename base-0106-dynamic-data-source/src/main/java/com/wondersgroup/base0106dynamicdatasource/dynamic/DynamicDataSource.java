package com.wondersgroup.base0106dynamicdatasource.dynamic;

import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author chenlin
 * @create 2019-11-15 10:04
 * @description: 动态切换数据源
 * @version：1.0
 *
 * 配置示例：
 *
 *
 *
 **/
@Component
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {


    private static final ThreadLocal<String> db = new ThreadLocal<String>();


    /**
     * 通过AOP 决定使用哪个数据源
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        String dbkey = db.get();
        if (StringUtils.hasText(dbkey)) {
            log.info("当前正在使用的数据源是{}",dbkey);
        }else{
            log.info("当前正在使用默认的数据源");
        }
        return dbkey;
    }


    /**
     * 为当前线程绑定数据源
     * @param dbKey
     */
    public static void set(String dbKey){
        db.set(dbKey);
    }

    /**
     * 取消当前线程绑定的数据源
     */
    public static void remove(){
        db.remove();
    }
}
