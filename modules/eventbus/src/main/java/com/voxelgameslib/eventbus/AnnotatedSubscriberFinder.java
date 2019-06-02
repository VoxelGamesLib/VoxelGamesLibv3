package com.voxelgameslib.eventbus;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import java.lang.reflect.Method;

/**
 * A {@link SubscriberFindingStrategy} for collecting all event handler methods that are marked with the {@link
 * Subscribe} annotation.
 *
 * <p>Original for Guava, licensed under the Apache License, Version 2.0.</p>
 */
class AnnotatedSubscriberFinder implements SubscriberFindingStrategy {

    /**
     * {@inheritDoc}
     *
     * This implementation finds all methods marked with a {@link Subscribe} annotation.
     */
    @Override
    public Multimap<Class<?>, EventHandler> findAllSubscribers(Object listener) {
        Multimap<Class<?>, EventHandler> methodsInListener = HashMultimap.create();
        Class<?> clazz = listener.getClass();
        while (clazz != null) {
            for (Method method : clazz.getMethods()) {
                Subscribe annotation = method.getAnnotation(Subscribe.class);
                method.setAccessible(true);

                if (annotation != null) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if (parameterTypes.length != 1) {
                        throw new IllegalArgumentException(
                                "Method " + method + " has @Subscribe annotation, but requires " +
                                        parameterTypes.length + " arguments.  Event handler methods " +
                                        "must require a single argument.");
                    }
                    Class<?> eventType = parameterTypes[0];
                    EventHandler handler = new MethodEventHandler(annotation.priority(), listener, method);
                    methodsInListener.put(eventType, handler);
                }
            }
            clazz = clazz.getSuperclass();
        }

        return methodsInListener;
    }

}
