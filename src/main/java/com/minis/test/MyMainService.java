package com.minis.test;

public class MyMainService {

    public MyMainService() {
    }

    private AServiceImpl aService;
    private BServiceImpl bService;

    public void setAService(AServiceImpl aService) {
        this.aService = aService;
        System.out.println(" ---> MainService set aService ");
    }

    public void setBService(BServiceImpl bService) {
        this.bService = bService;
        System.out.println(" ---> MainService set bService ");
    }

    public AServiceImpl getAService() {
        return aService;
    }
}
