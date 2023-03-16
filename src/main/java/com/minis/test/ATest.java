package com.minis.test;

import com.minis.ClassPathXmlApplicationContext;

public class ATest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        AIService aService = (AServieImpl) ctx.getBean("aService");
        aService.sayHello();
    }
}
