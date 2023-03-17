package com.minis.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleBeansFactory extends DefaultSingletonBeanRegistry implements BeansFactory,BeanDefinitionRegistry {

    private Map<String, BeanDefination> beanDefinitionMap = new ConcurrentHashMap<>(256);
    private List<String> beanDefinitionNames =  new ArrayList<>();

    public SimpleBeansFactory(){

    }

    @Override
    public void registerBeanDefinition(String name, BeanDefination beanDefination) {
        this.beanDefinitionMap.put(name,beanDefination);
        this.beanDefinitionNames.add(name);
        // postponed
        if(!beanDefination.isLazyInit()){
            try {
                getBean(name);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeBeanDefinition(String name) {
        this.beanDefinitionMap.remove(name);
        this.beanDefinitionNames.remove(name);
        this.removeSingleton(name);
    }

    @Override
    public BeanDefination getBeanDefinition(String name) {
        return this.beanDefinitionMap.get(name);
    }

    @Override
    public boolean containsBeanDefinition(String name) {
        return this.beanDefinitionMap.containsKey(name);
    }


    @Override
    public boolean isSingleton(String name) {
        return this.beanDefinitionMap.get(name).isSingleton();
    }

    @Override
    public boolean isPrototype(String name) {
        return this.beanDefinitionMap.get(name).isPrototype();
    }

    @Override
    public Class<?> getType(String name) {
        return this.beanDefinitionMap.get(name).getClass();
    }

    @Override
    public Object getBean(String beanName) throws BeansException {

        Object singleton = this.getSingleton(beanName);
        if(singleton == null){

            BeanDefination beanDefination = beanDefinitionMap.get(beanName);
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
}
