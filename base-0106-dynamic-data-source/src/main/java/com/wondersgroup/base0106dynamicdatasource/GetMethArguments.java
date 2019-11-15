package com.wondersgroup.base0106dynamicdatasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

import java.lang.reflect.Method;

/**
 * @author chenlin
 * @create 2019-11-15 10:55
 * @description: 通过spring的LocalVariableTableParameterNameDiscoverer 获取方法的参数，
 *              spring也是通过使用ASM通过字节码获取方法中参数的具体的名称
 * @version：1.0
 **/
@Slf4j
public class GetMethArguments {
    private static final LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
    public void testArguments(String test,Integer myInteger,boolean booleanTest){
    }
    public void test(){

    }
    public static void main(String[] args) {
        Method[] methods  = GetMethArguments.class.getMethods();
        for(Method method:methods){
            String[] paraNames = parameterNameDiscoverer.getParameterNames(method);

            log.info("methodName:"+method.getName());
            if(paraNames !=null){
                StringBuffer buffer = new StringBuffer();
                for(String string:paraNames){
                    buffer.append(string).append("\t");
                }
                log.info("parameArguments:"+buffer.toString());
            }else{
                log.info("无参数");
            }
        }
    }
}
