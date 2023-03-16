package com.minis.beans;

public interface BeansFactory {

    Object getBean(String beanName) throws BeansException;

    Boolean containsBean(String beanName);

    void registerBean(String beanName,Object obj);
}
