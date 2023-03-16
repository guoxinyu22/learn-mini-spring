package com.minis.test;

public class AServieImpl implements AIService {

    private String property1;

    public void setProperty1(String property1) {
        this.property1 = property1;
    }

    @Override
    public void sayHello() {
        System.out.println(" a service 1: say hello ");
        System.out.println(property1);
    }
}
