package com.minis.beans.factory;

import com.minis.beans.BeansException;

public interface BeansFactory {

    Object getBean(String beanName) throws BeansException;

    Boolean containsBean(String beanName);

    boolean isSingleton(String name);

    boolean isPrototype(String name);

    Class<?> getType(String name);
}
