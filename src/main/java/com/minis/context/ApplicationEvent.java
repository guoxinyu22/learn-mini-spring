package com.minis.context;

import java.util.EventObject;

/**
 * listen event
 */
public class ApplicationEvent extends EventObject {
    public ApplicationEvent(Object source) {
        super(source);
    }
}
