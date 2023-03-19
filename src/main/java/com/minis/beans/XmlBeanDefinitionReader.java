package com.minis.beans;

import com.minis.core.Resource;
import org.dom4j.Element;

import java.util.List;

public class XmlBeanDefinitionReader {

    SimpleBeansFactory simpleBeansFactory;

    public XmlBeanDefinitionReader(SimpleBeansFactory simpleBeansFactory) {
        this.simpleBeansFactory = simpleBeansFactory;
    }

    public void loadBeanDefiniations(Resource resource) {
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            String beanId = element.attributeValue("id");
            String beanClassName = element.attributeValue("class");

            // read properties
            List<Element> propertyElements = element.elements("property");
            PropertyValues propertyValues = new PropertyValues();
            for (Element e : propertyElements) {
                String name = e.attributeValue("name");
                String value = e.attributeValue("value");
                String type = e.attributeValue("type");
                propertyValues.addPropertyValue(new PropertyValue(type,name,value));
            }

            // read constructor
            List<Element> constructorElements = element.elements("property");
            ArgumentValues argumentValues = new ArgumentValues();
            for(Element e : constructorElements){
                String name = e.attributeValue("name");
                String type = e.attributeValue("type");
                String value = e.attributeValue("value");
                argumentValues.addArgumentValue(new ArgumentValue(type,name,value));
            }

            BeanDefinition beanDefinition = new BeanDefinition(beanId, beanClassName);
            beanDefinition.setPropertyValues(propertyValues);
            beanDefinition.setConstructorArgumentValues(argumentValues);
            this.simpleBeansFactory.registerBeanDefinition(beanId, beanDefinition);
        }
    }
}
