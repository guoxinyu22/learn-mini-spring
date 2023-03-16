package com.minis.beans;

import java.util.*;

public class ArgumentValues {

    private final Map<Integer, ArgumentValue> indexedArgumentValues = new HashMap<>(0);

    private final List<ArgumentValue> genericArgumentsValues = new LinkedList<>();

    private void addArgumentValue(Integer key, ArgumentValue newArgumentValue) {
        this.indexedArgumentValues.put(key, newArgumentValue);
    }

    public boolean hasIndexedArgumentValue(int index) {
        return this.indexedArgumentValues.containsKey(index);
    }

    public ArgumentValue getIndexedArgumentValue(int index) {
        return this.indexedArgumentValues.get(index);
    }

    public void addGenericArgumentValue(Object value, String type) {
        this.genericArgumentsValues.add(new ArgumentValue(value, type));
    }

    public void addGenericArgumentValue(ArgumentValue newValue) {
        if (newValue.getName() != null) {
            for(Iterator<ArgumentValue> it = this.genericArgumentsValues.iterator(); it.hasNext();){
                ArgumentValue currentValue = it.next();
                if(newValue.getName().equals(currentValue.getName())){
                    it.remove();
                }
            }
            this.genericArgumentsValues.add(newValue);
        }
    }

    public ArgumentValue getGenericArgumentValue(String requiredName){
        for(ArgumentValue valueHolder : this.genericArgumentsValues){
            if(valueHolder.getValue() != null && (requiredName == null || !valueHolder.getName().equals(requiredName))){
                continue;
            }
            return valueHolder;
        }
        return null;
    }

    public int getArgumentValueCount(){
        return this.genericArgumentsValues.size();
    }

    public boolean isEmpty(){
        return this.genericArgumentsValues.isEmpty();
    }
}
