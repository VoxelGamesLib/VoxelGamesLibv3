package com.voxelgameslib.eventbus.impl;

import com.google.common.base.Objects;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.InvocationTargetException;

/**
 * Event handler object for {@link DefaultEventBus} that is able to dispatch an event.
 *
 * <p>Original for Guava, licensed under the Apache License, Version 2.0.</p>
 */
public abstract class EventHandler<T> implements Comparable<EventHandler> {

    public enum Priority {
        VERY_EARLY,
        EARLY,
        NORMAL,
        LATE,
        VERY_LATE
    }

    private final Priority priority;

    /**
     * Create a new event handler.
     *
     * @param priority the priority
     */
    protected EventHandler(Priority priority) {
        checkNotNull(priority);
        this.priority = priority;
    }

    /**
     * Get the priority.
     *
     * @return the priority
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * Dispatch the given event.
     *
     * <p>Subclasses should override {@link #dispatch(Object)}.</p>
     *
     * @param event the event
     * @throws InvocationTargetException thrown if an exception is thrown during dispatch
     */
    public final void handleEvent(T event) throws InvocationTargetException {
        try {
            dispatch(event);
        } catch (Throwable t) {
            throw new InvocationTargetException(t);
        }
    }

    /**
     * Dispatch the event.
     *
     * @param event the event object
     * @throws Exception an exception that may be thrown
     */
    public abstract void dispatch(T event) throws Exception;

    @Override
    public int compareTo(EventHandler o) {
        return getPriority().ordinal() - o.getPriority().ordinal();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventHandler)) return false;
        EventHandler that = (EventHandler) o;
        return priority == that.priority;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(priority);
    }

    @Override
    public String toString() {
        return "EventHandler{" +
                "priority=" + priority +
                '}';
    }

}
