package com.minis.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleBeansFactory extends DefaultSingletonBeanRegistry implements BeansFactory {

    private Map<String, BeanDefination> beanDefinitions = new HashMap<>();

    public SimpleBeansFactory(){

    }

    @Override
    public Object getBean(String beanName) throws BeansException {

        Object singleton = this.getSingleton(beanName);
        if(singleton == null){

            BeanDefination beanDefination = beanDefinitions.get(beanName);
            if(beanDefination == null){
                throw new BeansException("No such bean:"+beanName);
            }

            try{
                singleton = Class.forName(beanDefination.getClassName()).newInstance();
            }catch (Exception e){
                e.printStackTrace();
            }

            this.registerSingleton(beanDefination.getId(),singleton);
        }
        return singleton;
    }

    @Override
    public Boolean containsBean(String beanName) {
        return containsSingleton(beanName);
    }

    @Override
    public void registerBean(String beanName, Object obj) {
        super.registerSingleton(beanName, obj);
    }

    public void registerBeanDefinition(BeanDefination beanDefination){
        this.beanDefinitions.put(beanDefination.getId(),beanDefination);
    }
}
