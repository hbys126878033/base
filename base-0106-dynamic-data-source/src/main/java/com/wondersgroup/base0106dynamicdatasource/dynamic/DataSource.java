package com.wondersgroup.base0106dynamicdatasource.dynamic;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author chenlin
 * @create 2019-11-15 10:06
 * @description: 动态数据源的注解，可以配置在类上，或者方法上，优先使用方法上，然后使用类型，最终使用默认值
 * @version：1.0
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface DataSource {
    /**
     * 指定数据源的KEY
     * @return
     */
    String value() default "";
}
