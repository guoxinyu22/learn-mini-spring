package com.minis.test;

public class AServiceImpl implements AIService {

    private String property1;

    private String property2;

    private BServiceImpl bService;

    public void setbService(BServiceImpl bService) {
        this.bService = bService;
        System.out.println(" AService set BService ");
    }

    public AServiceImpl(String name, Integer level) {
//        this.property1 = property1;
//        this.property2 = property2;
        System.out.println(" constructor property1 value is : " + name);
        System.out.println(" constructor property2 value is : " + level);
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
