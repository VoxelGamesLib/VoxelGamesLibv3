package com.voxelgameslib.util;

import com.google.inject.Inject;

public class UtilInjectionPoint implements VGLInjectionPoint{

    @Inject
    public static UtilModuleFactory utilModuleFactory;
}
