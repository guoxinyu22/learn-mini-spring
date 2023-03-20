package com.minis.test;

import com.minis.beans.BeansException;
import com.minis.context.ClassPathXmlApplicationContext;

import java.lang.reflect.Method;

public class ATest {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml",true);
        MyMainService myMainService = (MyMainService) ctx.getBean("myMainService");




    }
}
