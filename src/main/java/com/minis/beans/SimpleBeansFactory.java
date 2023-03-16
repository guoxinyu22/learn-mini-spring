package com.minis.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleBeansFactory implements BeansFactory {

    private List<BeanDefination> beanDefinations = new ArrayList<>();
    private List<String> beanNames = new ArrayList<>();
    private Map<String, Object> singletons = new HashMap<>();

    public SimpleBeansFactory(){

    }

    @Override
    public Object getBean(String beanName) throws BeansException {

        Object singleton = singletons.get(beanName);
        if(singleton == null){
            int i = beanNames.indexOf(beanName);
            if(i == -1){
                throw new BeansException("no such bean");
            }else {
                BeanDefination beanDefination = beanDefinations.get(i);
                try{
                    singleton = Class.forName(beanDefination.getClassName()).newInstance();
                }catch (Exception e){
                    e.printStackTrace();
                }
                singletons.put(beanDefination.getId(),singleton);
            }
        }
        return singleton;
    }

    @Override
    public void registerBeanDefiniation(BeanDefination beanDefination) {
        this.beanDefinations.add(beanDefination);
        this.beanNames.add(beanDefination.getId());
    }
}
