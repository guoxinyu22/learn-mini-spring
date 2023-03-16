package com.minis.beans;

import com.minis.core.Resource;
import org.dom4j.Element;

public class XmlBeanDefinitionReader {

    BeansFactory beansFactory;

    public XmlBeanDefinitionReader(BeansFactory beansFactory) {
        this.beansFactory = beansFactory;
    }

    public void loadBeanDefiniations(Resource resource){
        while (resource.hasNext()){
            Element element = (Element) resource.next();
            String beanId = element.attributeValue("id");
            String beanClassName = element.attributeValue("class");
            BeanDefination beanDefination = new BeanDefination(beanId, beanClassName);
            this.beansFactory.registerBeanDefiniation(beanDefination);
        }
    }
}
