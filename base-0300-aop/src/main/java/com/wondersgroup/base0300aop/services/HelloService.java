package com.wondersgroup.base0300aop.services;

import org.springframework.context.annotation.ComponentScans;
import org.springframework.stereotype.Component;

/**
 * @author chenlin
 * @create 2019-06-05 22:04
 * @description: TODO
 * @version：1.0
 **/
@Component
public class HelloService {


    public void say() {

        System.out.println("hello world!!!");
    }
}
