package com.voxelgameslib.eventbus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.voxelgameslib.eventbus.EventHandler.Priority;

/**
 * Used to mark methods as event handlers.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Subscribe {

    /**
     * The priority as far as order of dispatching is concerned.
     *
     * @return the priority
     */
    Priority priority() default Priority.NORMAL;

}
