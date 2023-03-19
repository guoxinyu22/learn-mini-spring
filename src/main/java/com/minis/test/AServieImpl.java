package com.minis.test;

public class AServieImpl implements AIService {

    private String property1;

    private String property2;

    public AServieImpl(String property1, String property2) {
//        this.property1 = property1;
//        this.property2 = property2;
        System.out.println(" constructor property1 value is : " + property1);
        System.out.println(" constructor property2 value is : " + property2);
    }

    public void setProperty1(String property1) {
        this.property1 = property1;
        System.out.println(" set property1 value is : " + property1);
    }

    public void setProperty2(String property2) {
        this.property2 = property2;
        System.out.println(" set property2 value is : " + property2);
    }

    @Override
    public void sayHello() {
        System.out.println(" a service 1: say hello ");
        System.out.println(property1);
    }
}
