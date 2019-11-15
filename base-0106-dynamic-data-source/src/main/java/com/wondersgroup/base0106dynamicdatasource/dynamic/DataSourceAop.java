package com.wondersgroup.base0106dynamicdatasource.dynamic;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author chenlin
 * @create 2019-11-15 10:17
 * @description: AOP实现动态切换数据源
 * @version：1.0
 **/

/***
 * @Aspect ：表示当前是一个切面类
 * @Component：表示把当前组件注入到IOC容器中，不然AOP不会生效
 * @Order :表示当前AOP的执行顺序，应该现在@Transcation注解之前执行
 */
@Aspect
@Component
@Order(-1)
public class DataSourceAop {

    /**
     * within表示注解在类上
     * annotation 表示在方法上
     *
     * @param joinPoint
     * @return
     */
    @Around(value="@within(com.wondersgroup.base0106dynamicdatasource.dynamic.DataSource) || " +
            "@annotation(com.wondersgroup.base0106dynamicdatasource.dynamic.DataSource)")
    public Object handlerDynamicDataSource(ProceedingJoinPoint joinPoint) throws Throwable {

        try {

            Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
            DataSource annotationClass = method.getAnnotation(DataSource.class);//获取方法上的注解

            if (annotationClass == null) {
                annotationClass = joinPoint.getTarget().getClass().getAnnotation(DataSource.class);//获取类上面的注解
            }

            if (annotationClass != null) {
                //获取注解上的数据源的值的信息
                String dataSourceKey = annotationClass.value();
                if(dataSourceKey !=null){
                    //给当前的执行SQL的操作设置特殊的数据源的信息
                    DynamicDataSource.set(dataSourceKey);
                }
            }
            return joinPoint.proceed();
        } finally {
            DynamicDataSource.remove();
        }
    }

}
