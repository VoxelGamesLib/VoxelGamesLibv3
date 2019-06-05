package com.voxelgameslib.text;

import com.google.inject.Inject;

import com.voxelgameslib.util.VGLInjectionPoint;

public class TextInjectionPoint implements VGLInjectionPoint {

    @Inject
    public static TextModuleFactory textModuleFactory;
}
