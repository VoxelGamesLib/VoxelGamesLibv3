package com.voxelgameslib.eventbus;

/**
 * Eventbus, allowing
 */
public interface EventBus {

    void subscribe(Object object);

    void unsubscribe(Object object);

    void post(Object event);
}
