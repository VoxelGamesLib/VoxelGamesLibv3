package com.voxelgameslib.eventbus;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import com.voxelgameslib.util.Identifier;
import com.voxelgameslib.util.ModuleFactory;

public interface EventBusModuleFactory extends ModuleFactory {

    @Inject
    EventBus eventBus(@Assisted("identifier")Identifier  identifier);
}
