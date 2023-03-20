package com.minis.test;

public class BServiceImpl {

    private MyMainService myMainService;

    public BServiceImpl() {
    }

    public void setMyMainService(MyMainService myMainService) {
        this.myMainService = myMainService;
        System.out.println(" ---> BService set mainService !");
    }

    public void saySomething() {
        this.myMainService.getAService().sayHello();
    }
}
