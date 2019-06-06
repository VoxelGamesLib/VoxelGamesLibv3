package com.voxelgameslib.script;

import com.google.inject.Inject;

import com.voxelgameslib.util.VGLInjectionPoint;

public class ScriptInjectionPoint implements VGLInjectionPoint {

    @Inject
    public static ScriptModuleFactory scriptModuleFactory;
}
