package com.voxelgameslib.eventbus;

import java.util.function.Consumer;

import com.voxelgameslib.util.Identifier;

/**
 * Eventbus, allowing
 */
public interface EventBus {

    <T> EventHandler<T> subscribe(Class<T> clazz, Consumer<T> handler);

    <T> EventHandler<T> subscribe(Class<T> clazz, Consumer<T> handler, Priority priority);

    void subscribe(Object object);

    void unsubscribe(Object object);

    <T> void post(T event);

    Identifier getIdentifier();

    static EventBus newEventBus(Identifier identifier) {
        return EventBusInjectionPoint.eventBusModuleFactory.eventBus(identifier);
    }
}
