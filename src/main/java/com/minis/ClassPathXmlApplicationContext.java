package com.minis;

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
public class ClassPathXmlApplicationContext {

    private List<BeanDefination> beanDefinations = new ArrayList<>();

    private Map<String,Object> singletons = new HashMap<>();

    /**
     * Read and Instantiate Java beans from xml
     * @param fileName bean definition xml file
     */
    public ClassPathXmlApplicationContext(String fileName) {
        this.readXML(fileName);
        this.instanceBeans();
    }

    // read Bean definitions from xml and store them in definition list
    private void readXML(String fileName){
        try{
            SAXReader saxReader = new SAXReader();
            URL xmlPath = this.getClass().getClassLoader().getResource(fileName);
            Document document = saxReader.read(xmlPath);
            Element rootElement = document.getRootElement();
            // foreach every Java Class definition in xml, add BeanDefinition to list
            for (Element element :(List<Element>) rootElement.elements()) {
                String beanID = element.attributeValue("id");
                String beanClassName = element.attributeValue("class");
                BeanDefination beanDefination = new BeanDefination(beanID, beanClassName);
                beanDefinations.add(beanDefination);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // using reflect to instantiate Java Bean, and store them in singletons
    private void instanceBeans(){
        for (BeanDefination beanDefination : beanDefinations) {
            try {
                singletons.put(beanDefination.getId(),Class.forName(beanDefination.getClassName()).newInstance());
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // obtain Java instantiation
    public Object getBean(String beanName){
        return singletons.get(beanName);
    }

}
