package com.voxelgameslib.eventbus.impl;

import com.google.common.collect.Multimap;

import com.voxelgameslib.eventbus.EventHandler;

/**
 * A method for finding event handler methods in objects, for use by {@link DefaultEventBus}.
 */
interface SubscriberFindingStrategy {

    /**
     * Finds all suitable event handler methods in {@code source}, organizes them by the type of event they handle, and
     * wraps them in {@link DefaultEventHandler}s.
     *
     * @param source object whose handlers are desired.
     * @return DefaultEventHandler objects for each handler method, organized by event type.
     * @throws IllegalArgumentException if {@code source} is not appropriate for this strategy (in ways that this
     *                                  interface does not define).
     */
    Multimap<Class<?>, EventHandler<?>> findAllSubscribers(Object source);

}
