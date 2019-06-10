package com.voxelgameslib.eventbus.impl;

import com.google.inject.Binder;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import com.voxelgameslib.eventbus.EventBus;
import com.voxelgameslib.eventbus.EventBusModuleFactory;
import com.voxelgameslib.util.Identifier;
import com.voxelgameslib.util.ImplementsModule;
import com.voxelgameslib.util.VGLModule;

@ImplementsModule(value = EventBusModuleFactory.class, prio = ImplementsModule.VGL_PRIO)
public class EventBusImplModule implements VGLModule {

    @Override
    public void configure(Binder binder) {
        binder.install(new FactoryModuleBuilder()
                .implement(EventBus.class, DefaultEventBus.class)
                .build(EventBusModuleFactory.class));
    }

    @Override
    public Identifier identifier() {
        return Identifier.ofVGL("DefaultEventBusModule");
    }
}
