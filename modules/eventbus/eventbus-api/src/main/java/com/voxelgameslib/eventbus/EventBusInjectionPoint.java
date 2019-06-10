package com.voxelgameslib.eventbus;

import com.google.inject.Inject;

import com.voxelgameslib.util.VGLInjectionPoint;

public class EventBusInjectionPoint implements VGLInjectionPoint {

    @Inject
    public static EventBusModuleFactory eventBusModuleFactory;
}
