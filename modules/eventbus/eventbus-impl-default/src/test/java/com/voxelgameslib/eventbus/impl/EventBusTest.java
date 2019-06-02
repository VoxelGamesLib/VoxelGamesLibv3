package com.voxelgameslib.eventbus.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.voxelgameslib.eventbus.impl.impl.DefaultEventBus;
import com.voxelgameslib.eventbus.impl.impl.EventHandler;
import com.voxelgameslib.eventbus.impl.impl.Subscribe;

import static org.junit.jupiter.api.Assertions.*;

class EventBusTest {

    private static final class Event {

    }

    private static final class Subscriber {

        private final List<Event> events = new ArrayList<>();

        @Subscribe
        public void onEvent(Event event) {
            events.add(event);
        }

    }

    private static final class InvalidSubscriber {

        @Subscribe
        public void onEvent(Event event, String fail) {

        }

    }

    private DefaultEventBus eventBus;

    @BeforeEach
    void setup() {
        eventBus = new DefaultEventBus();
    }

    @Test
    void testRegister_annotations_happyday() {
        Subscriber subscriber = new Subscriber();
        eventBus.subscribe(subscriber);
        Event e1 = new Event();
        eventBus.post(e1);
        Event e2 = new Event();
        eventBus.post(e2);
        assertEquals(Arrays.asList(e1, e2), subscriber.events);
    }

    @Test
    void testUnregister_annotations_happyday() {
        Subscriber subscriber = new Subscriber();
        eventBus.subscribe(subscriber);
        Event e1 = new Event();
        eventBus.post(e1);
        eventBus.unsubscribe(subscriber);
        Event e2 = new Event();
        eventBus.post(e2);
        assertEquals(List.of(e1), subscriber.events);
    }

    @Test
    void testRegister_functional_happyday() {
        List<Event> events = new ArrayList<>();
        eventBus.subscribe(Event.class, events::add, EventHandler.Priority.NORMAL);
        Event e1 = new Event();
        eventBus.post(e1);
        Event e2 = new Event();
        eventBus.post(e2);
        assertEquals(Arrays.asList(e1, e2), events);
    }

    @Test
    void testUnregister_functional_happyday() {
        List<Event> events = new ArrayList<>();
        EventHandler<Event> handler = eventBus.subscribe(Event.class, events::add, EventHandler.Priority.NORMAL);
        Event e1 = new Event();
        eventBus.post(e1);
        eventBus.unsubscribe(Event.class, handler); // nastly, but I don't care rn
        Event e2 = new Event();
        eventBus.post(e2);
        assertEquals(List.of(e1), events);
    }

    @Test
    void testWithMultipleParams_throwException() {
        InvalidSubscriber subscriber = new InvalidSubscriber();
        assertThrows(IllegalArgumentException.class, () -> eventBus.subscribe(subscriber));
    }

    @Test
    void testErrorInDispatch_errorGetsCaught() {
        eventBus.subscribe(Event.class, (event) -> {
            throw new RuntimeException();
        });
        eventBus.post(new Event());
    }
}
