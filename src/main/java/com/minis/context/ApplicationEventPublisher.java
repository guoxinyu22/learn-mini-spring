package com.minis.context;

/**
 * publish event
 */
public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent applicationEvent);
}
