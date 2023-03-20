package com.minis.context;

import com.minis.beans.*;
import com.minis.beans.factory.BeansFactory;
import com.minis.beans.factory.support.SimpleBeansFactory;
import com.minis.beans.factory.xml.XmlBeanDefinitionReader;
import com.minis.core.ClassPathXmlResource;
import com.minis.core.Resource;

/**
 * Read Java Bean information from xml file
 */
public class ClassPathXmlApplicationContext implements BeansFactory,ApplicationEventPublisher{

    BeansFactory beansFactory;

    /**
     * Read and Instantiate Java beans from xml
     * @param fileName bean definition xml file
     */
    public ClassPathXmlApplicationContext(String fileName,boolean isRefresh) {
        Resource resource = new ClassPathXmlResource(fileName);
        SimpleBeansFactory beansFactory = new SimpleBeansFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beansFactory);
        reader.loadBeanDefiniations(resource);
        this.beansFactory = beansFactory;
        if(isRefresh){
            beansFactory.refresh();
        }
    }


    @Override
    public Object getBean(String beanName) throws BeansException {
        return beansFactory.getBean(beanName);
    }

    @Override
    public Boolean containsBean(String beanName) {
        return this.beansFactory.containsBean(beanName);
    }

    @Override
    public boolean isSingleton(String name) {
        return false;
    }

    @Override
    public boolean isPrototype(String name) {
        return false;
    }

    @Override
    public Class<?> getType(String name) {
        return null;
    }

    @Override
    public void publishEvent(ApplicationEvent applicationEvent) {

    }
}
