package com.minis.beans;

public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String name,BeanDefination beanDefination);

    void removeBeanDefinition(String name);

    BeanDefination getBeanDefinition(String name);

    boolean containsBeanDefinition(String name);
}
