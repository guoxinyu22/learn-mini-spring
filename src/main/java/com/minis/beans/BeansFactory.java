package com.minis.beans;

public interface BeansFactory {

    Object getBean(String beanName) throws BeansException;

    void registerBeanDefiniation(BeanDefination beanDefination);
}
