package com.minis.beans;

public interface BeansFactory {

    Object getBean(String beanName) throws BeansException;

    Boolean containsBean(String beanName);

    boolean isSingleton(String name);

    boolean isPrototype(String name);

    Class<?> getType(String name);
}
