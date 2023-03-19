package com.minis.beans;

import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleBeansFactory extends DefaultSingletonBeanRegistry implements BeansFactory, BeanDefinitionRegistry {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);
    private List<String> beanDefinitionNames = new ArrayList<>();


    public SimpleBeansFactory() {

    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(name, beanDefinition);
        this.beanDefinitionNames.add(name);
        // postponed
        if (!beanDefinition.isLazyInit()) {
            try {
                getBean(name);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeBeanDefinition(String name) {
        this.beanDefinitionMap.remove(name);
        this.beanDefinitionNames.remove(name);
        this.removeSingleton(name);
    }

    @Override
    public BeanDefinition getBeanDefinition(String name) {
        return this.beanDefinitionMap.get(name);
    }

    @Override
    public boolean containsBeanDefinition(String name) {
        return this.beanDefinitionMap.containsKey(name);
    }


    @Override
    public boolean isSingleton(String name) {
        return this.beanDefinitionMap.get(name).isSingleton();
    }

    @Override
    public boolean isPrototype(String name) {
        return this.beanDefinitionMap.get(name).isPrototype();
    }

    @Override
    public Class<?> getType(String name) {
        return this.beanDefinitionMap.get(name).getClass();
    }

    @Override
    public Object getBean(String beanName) throws BeansException {

        Object singleton = this.getSingleton(beanName);
        if (singleton == null) {

            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if (beanDefinition == null) {
                throw new BeansException("No such bean:" + beanName);
            }
            this.registerSingleton(beanDefinition.getId(), createBean(beanDefinition));
        }
        return singleton;
    }

    @Override
    public Boolean containsBean(String beanName) {
        return containsSingleton(beanName);
    }

    private Object createBean(BeanDefinition beanDefinition) {
        Object object = null;
        Class<?> clazz = null;
        Constructor<?> constructor = null;
        try {
            clazz = Class.forName(beanDefinition.getClassName());

            ArgumentValues constructorArgumentValues = beanDefinition.getConstructorArgumentValues();

            // initialize constructor
            if (constructorArgumentValues != null && !constructorArgumentValues.isEmpty()) {
                Class<?>[] paramTypes = new Class<?>[constructorArgumentValues.getArgumentCount()];
                Object[] paramValues = new Object[constructorArgumentValues.getArgumentCount()];

                for (int i = 0; i < constructorArgumentValues.getArgumentCount(); i++) {
                    ArgumentValue argumentValue = constructorArgumentValues.getIndexedArgumentValue(i);
                    String type = argumentValue.getType();
                    String name = argumentValue.getName();
                    Object value = argumentValue.getValue();
                    // convert value type
                    if ("string".equals(type) || "String".equals(type) || "java.lang.String".equals(type)) {
                        paramTypes[i] = String.class;
                        paramValues[i] = (String) value;
                    } else if ("Integer".equals(type) || "int".equals(type) || "java.lang.Integer".equals(type)) {
                        paramTypes[i] = Integer.class;
                        paramValues[i] = Integer.valueOf((String) value);
                    } else {
                        paramTypes[i] = String.class;
                        paramValues[i] = (String) value;
                    }
                }
                try {
                    constructor = clazz.getConstructor(paramTypes);
                    object = constructor.newInstance(paramValues);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                // no constructor
                object = clazz.newInstance();
            }

            // initialize properties
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            if (propertyValues != null && !propertyValues.isEmpty()) {
                for (int i = 0; i < propertyValues.size(); i++) {
                    PropertyValue propertyValue = propertyValues.getPropertyValueList().get(i);
                    String type = propertyValue.getType();
                    String name = propertyValue.getName();
                    Object value = propertyValue.getValue();
                    boolean ref = propertyValue.isRef();

                    Class<?>[] paramTypes = new Class<?>[1];
                    Object[] paramValues = new Object[1];

                    if (!ref) {
                        if ("string".equals(type) || "String".equals(type) || "java.lang.String".equals(type)) {
                            paramTypes[0] = String.class;
                        } else if ("Integer".equals(type) || "int".equals(type) || "java.lang.Integer".equals(type)) {
                            paramTypes[0] = Integer.class;
                        } else {
                            paramTypes[0] = String.class;
                        }
                        paramValues[0] = value;
                    } else {
                        // is ref, create dependent beans
                        paramTypes[0] = Class.forName(type);
                        paramValues[0] = getBean((String) name);
                    }

                    String methodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
                    Method method = null;
                    try {
                        method = clazz.getMethod(methodName, paramTypes);
                        method.invoke(object, paramValues);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }
}
