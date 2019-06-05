package com.voxelgameslib.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ImplementsModule {

    int VGL_PRIO = 0;

    Class<? extends ModuleFactory> value();

    int prio() default 100;
}
