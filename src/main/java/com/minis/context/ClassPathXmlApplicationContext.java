package com.minis.context;

import com.minis.beans.*;
import com.minis.core.ClassPathXmlResource;
import com.minis.core.Resource;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Read Java Bean information from xml file
 */
public class ClassPathXmlApplicationContext implements BeansFactory{

    BeansFactory beansFactory;

    /**
     * Read and Instantiate Java beans from xml
     * @param fileName bean definition xml file
     */
    public ClassPathXmlApplicationContext(String fileName) {
        Resource resource = new ClassPathXmlResource(fileName);
        SimpleBeansFactory beansFactory = new SimpleBeansFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beansFactory);
        reader.loadBeanDefiniations(resource);
        this.beansFactory = beansFactory;
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
    public void registerBean(String beanName, Object obj) {
        this.beansFactory.registerBean(beanName,obj);
    }
}
