package com.voxelgameslib.eventbus;

import com.voxelgameslib.util.Identifier;

/**
 * Eventbus, allowing
 */
public interface EventBus {

    void subscribe(Object object);

    void unsubscribe(Object object);

    void post(Object event);

    Identifier getIdentifier();

    static EventBus newEventBus(Identifier identifier) {
        return EventBusInjectionPoint.eventBusModuleFactory.eventBus(identifier);
    }
}
