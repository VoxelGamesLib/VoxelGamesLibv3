package com.voxelgameslib.game;

import com.google.inject.Inject;

import com.voxelgameslib.util.GuiceFactory;
import com.voxelgameslib.util.VGLInjectionPoint;

public class GameInjectionPoint implements VGLInjectionPoint {

    @Inject
    public static GameModuleFactory gameModuleFactory;

    @Inject
    public static GuiceFactory guiceFactory;
}
