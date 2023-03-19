package com.minis.beans;

import com.minis.core.Resource;
import org.dom4j.Element;

import java.util.ArrayList;
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


            // read constructor
            List<Element> constructorElements = element.elements("constructor-arg");
            ArgumentValues argumentValues = new ArgumentValues();
            for (Element e : constructorElements) {
                String name = e.attributeValue("name");
                String type = e.attributeValue("type");
                String value = e.attributeValue("value");
                argumentValues.addArgumentValue(new ArgumentValue(type, name, value));
            }



            // read properties
            List<Element> propertyElements = element.elements("property");
            PropertyValues propertyValues = new PropertyValues();

            List<String> refs = new ArrayList<>();
            for (Element e : propertyElements) {
                String name = e.attributeValue("name");
                String value = e.attributeValue("value");
                String type = e.attributeValue("type");
                String ref = e.attributeValue("ref");

                boolean isRef = false;
                String pv = "";
                if (value != null && !"".equals(value)) {
                    // contains value , not ref
                    isRef = false;
                    pv = value;
                } else if (ref != null && !"".equals(ref)) {
                    // contains ref
                    isRef = true;
                    pv = ref;
                    refs.add(ref);
                }
                propertyValues.addPropertyValue(new PropertyValue(type, name, pv, isRef));
            }


            BeanDefinition beanDefinition = new BeanDefinition(beanId, beanClassName);
            beanDefinition.setPropertyValues(propertyValues);
            beanDefinition.setConstructorArgumentValues(argumentValues);

            // set refs
            beanDefinition.setDependsOn(refs.toArray(new String[0]));

            this.simpleBeansFactory.registerBeanDefinition(beanId, beanDefinition);
        }
    }
}
