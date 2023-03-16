package com.minis.beans;

public class ArgumentValue {
    private String type;
    private Object value;
    private String name;

    public ArgumentValue(Object value, String type) {
        this.type = type;
        this.value = value;
    }

    public ArgumentValue(String type, Object value, String name) {
        this.type = type;
        this.value = value;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
