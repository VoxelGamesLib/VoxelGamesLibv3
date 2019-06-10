package com.voxelgameslib.eventbus;

import java.lang.reflect.InvocationTargetException;

public interface EventHandler<T> extends Comparable<EventHandler> {
    /**
     * Dispatch the event.
     *
     * @param event the event object
     * @throws Exception an exception that may be thrown
     */
    void dispatch(T event) throws Exception;

    void handleEvent(T event) throws InvocationTargetException;

    Priority getPriority();

}
