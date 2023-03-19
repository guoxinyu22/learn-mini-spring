package com.minis.test;

import com.minis.beans.BeansException;
import com.minis.context.ClassPathXmlApplicationContext;

public class ATest {
    public static void main(String[] args) throws BeansException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        MainService mainService = (MainService) ctx.getBean("mainService");
        AServiceImpl aService1 = mainService.getaService();
        aService1.sayHello();

//        AIService aService = (AServiceImpl) ctx.getBean("aService");
//        aService.sayHello();
    }
}
