package com.minis.test;

public class MainService {

    private AServiceImpl aService;

    public void setaService(AServiceImpl aService) {
        this.aService = aService;
        System.out.println(" MainService set aService ");
    }

    public AServiceImpl getaService() {
        return aService;
    }
}
