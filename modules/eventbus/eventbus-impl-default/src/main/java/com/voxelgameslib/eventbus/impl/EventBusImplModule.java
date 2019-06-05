package com.voxelgameslib.eventbus.impl;

import com.google.inject.Binder;
import com.google.inject.Module;

import com.voxelgameslib.eventbus.EventBus;

public class EventBusImplModule implements Module {
    @Override
    public void configure(Binder binder) {
        binder.bind(EventBus.class).toInstance(new DefaultEventBus());
    }
}
