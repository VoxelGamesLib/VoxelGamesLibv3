package com.voxelgameslib.eventbus.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Holds a cache of class hierarchy.
 */
class HierarchyCache {

    private final LoadingCache<Class<?>, Set<Class<?>>> cache = CacheBuilder.newBuilder()
            .weakKeys()
            .build(CacheLoader.from(this::build));

    Set<Class<?>> get(Class<?> concreteClass) {
        return cache.getUnchecked(concreteClass);
    }

    private Set<Class<?>> build(Class<?> concreteClass) {
        List<Class<?>> parents = Lists.newLinkedList();
        Set<Class<?>> classes = Sets.newHashSet();

        parents.add(concreteClass);

        while (!parents.isEmpty()) {
            Class<?> clazz = parents.remove(0);
            classes.add(clazz);

            Class<?> parent = clazz.getSuperclass();
            //noinspection ConstantConditions - false positive, parent can be null
            if (parent != null) {
                parents.add(parent);
            }

            Collections.addAll(parents, clazz.getInterfaces());
        }

        return classes;
    }

}
