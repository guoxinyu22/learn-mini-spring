package com.minis.test;

public class BServiceImpl {

    private MainService mainService;

    public void setMainService(MainService mainService) {
        this.mainService = mainService;
        System.out.println("BService set mainService !");
    }
}
