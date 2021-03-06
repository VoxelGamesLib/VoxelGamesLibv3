package com.voxelgameslib.eventbus.impl;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.SetMultimap;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Consumer;

import com.voxelgameslib.eventbus.EventBus;
import com.voxelgameslib.eventbus.EventHandler;
import com.voxelgameslib.eventbus.Priority;
import com.voxelgameslib.util.Identifier;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Dispatches events to listeners, and provides ways for listeners to register themselves.
 *
 * <p>This class is based on Guava's {@link DefaultEventBus} but priority is supported
 * and events are dispatched at the time of call, rather than being queued up. This does allow dispatching during an
 * in-progress dispatch.</p>
 */
public final class DefaultEventBus implements EventBus {

    private final Logger logger = LoggerFactory.getLogger(DefaultEventBus.class);

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    private final SetMultimap<Class<?>, EventHandler<?>> handlersByType =
            HashMultimap.create();

    /**
     * Strategy for finding handler methods in registered objects.  Currently, only the {@link
     * AnnotatedSubscriberFinder} is supported, but this is encapsulated for future expansion.
     */
    private final SubscriberFindingStrategy finder = new AnnotatedSubscriberFinder();

    private HierarchyCache flattenHierarchyCache = new HierarchyCache();

    private Identifier identifier;

    @Inject
    DefaultEventBus(@Assisted("identifier") Identifier identifier) {
        this.identifier = identifier;
    }

    /**
     * Registers the given handler for the given class with the default priority to receive events
     *
     * @param <T>     the type of events to receive
     * @param clazz   the event class to register
     * @param handler the handler to register
     * @return the registered handler
     */
    @Override
    public <T> EventHandler<T> subscribe(Class<T> clazz, Consumer<T> handler) {
        return subscribe(clazz, handler, Priority.NORMAL);
    }

    /**
     * Registers the given handler for the given class with the given priority to receive events
     *
     * @param <T>      the type of events to receive
     * @param clazz    the event class to register
     * @param handler  the handler to register
     * @param priority the priority to use
     * @return the registered handler
     */
    @Override
    public <T> EventHandler<T> subscribe(Class<T> clazz, Consumer<T> handler, Priority priority) {
        EventHandler<T> eventHandler = new DefaultEventHandler<>(priority) {
            @Override
            public void dispatch(T event) {
                handler.accept(event);
            }
        };
        subscribe(clazz, eventHandler);
        return eventHandler;
    }

    /**
     * Registers the given handler for the given class to receive events.
     *
     * @param clazz   the event class to register
     * @param handler the handler to register
     */
    public <T> void subscribe(Class<T> clazz, EventHandler<T> handler) {
        checkNotNull(clazz);
        checkNotNull(handler);
        lock.writeLock().lock();
        try {
            handlersByType.put(clazz, handler);
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Registers the given handler for the given class to receive events.
     *
     * @param handlers a map of handlers
     */
    public void subscribeAll(Multimap<Class<?>, EventHandler<?>> handlers) {
        checkNotNull(handlers);
        lock.writeLock().lock();
        try {
            handlersByType.putAll(handlers);
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Unregisters the given handler for the given class.
     *
     * @param clazz   the class
     * @param handler the handler
     */
    public void unsubscribe(Class<?> clazz, EventHandler<?> handler) {
        checkNotNull(clazz);
        checkNotNull(handler);
        lock.writeLock().lock();
        try {
            handlersByType.remove(clazz, handler);
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Unregisters the given handlers.
     *
     * @param handlers a map of handlers
     */
    public void unsubscribeAll(Multimap<Class<?>, EventHandler<?>> handlers) {
        checkNotNull(handlers);
        lock.writeLock().lock();
        try {
            for (Map.Entry<Class<?>, Collection<EventHandler<?>>> entry : handlers.asMap().entrySet()) {
                handlersByType.get(entry.getKey()).removeAll(entry.getValue());
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Registers all handler methods on {@code object} to receive events. Handler methods are selected and classified
     * using this DefaultEventBus's {@link SubscriberFindingStrategy}; the default strategy is the {@link
     * AnnotatedSubscriberFinder}.
     *
     * @param object object whose handler methods should be registered.
     */
    @Override
    public void subscribe(Object object) {
        subscribeAll(finder.findAllSubscribers(object));
    }

    /**
     * Unregisters all handler methods on a registered {@code object}.
     *
     * @param object object whose handler methods should be unregistered.
     * @throws IllegalArgumentException if the object was not previously registered.
     */
    @Override
    public void unsubscribe(Object object) {
        unsubscribeAll(finder.findAllSubscribers(object));
    }

    /**
     * Posts an event to all registered handlers.  This method will return successfully after the event has been posted
     * to all handlers, and regardless of any exceptions thrown by handlers.
     *
     * @param event event to post.
     */
    @Override
    public <T> void post(T event) {
        List<EventHandler<?>> dispatching = new ArrayList<>();

        Set<Class<?>> dispatchTypes = flattenHierarchyCache.get(event.getClass());
        lock.readLock().lock();
        try {
            for (Class<?> eventType : dispatchTypes) {
                Set<EventHandler<?>> wrappers = handlersByType.get(eventType);

                if (wrappers != null && !wrappers.isEmpty()) {
                    dispatching.addAll(wrappers);
                }
            }
        } finally {
            lock.readLock().unlock();
        }

        Collections.sort(dispatching);

        for (EventHandler<?> handler : dispatching) {
            //noinspection unchecked
            dispatch(event,(EventHandler<T>) handler);
        }
    }

    @Override
    public Identifier getIdentifier() {
        return identifier;
    }

    /**
     * Dispatches {@code event} to the handler in {@code handler}.
     *
     * @param event   event to dispatch.
     * @param handler handler that will call the handler.
     */
    private <T> void dispatch(T event, EventHandler<T> handler) {
        try {
            handler.handleEvent(event);
        } catch (InvocationTargetException e) {
            logger.error("Could not dispatch event: " + event + " to handler " + handler, e);
        }
    }

}
