package com.minis;

/**
 * Defination of Java Bean
 */
public class BeanDefination {

    private String id;

    private String className;

    public BeanDefination(String id, String className) {
        this.id = id;
        this.className = className;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
