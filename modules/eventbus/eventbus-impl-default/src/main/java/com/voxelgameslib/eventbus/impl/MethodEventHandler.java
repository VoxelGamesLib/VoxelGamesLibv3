package com.voxelgameslib.eventbus.impl;

import com.google.common.base.Objects;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Method;

import com.voxelgameslib.eventbus.Priority;

/**
 * Invokes a {@link Method} to dispatch an event.
 */
public class MethodEventHandler<T> extends DefaultEventHandler<T> {

    private final Object object;
    private final Method method;

    /**
     * Create a new event handler.
     *
     * @param priority the priority
     * @param method   the method
     */
    public MethodEventHandler(Priority priority, Object object, Method method) {
        super(priority);
        checkNotNull(method);
        this.object = object;
        this.method = method;
    }

    /**
     * Get the method.
     *
     * @return the method
     */
    public Method getMethod() {
        return method;
    }

    @Override
    public void dispatch(T event) throws Exception {
        method.invoke(object, event);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MethodEventHandler)) return false;
        if (!super.equals(o)) return false;
        MethodEventHandler that = (MethodEventHandler) o;
        return Objects.equal(object, that.object) &&
                Objects.equal(method, that.method);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), object, method);
    }
}
