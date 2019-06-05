package com.voxelgameslib;

import com.google.inject.Module;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.voxelgameslib.eventbus.EventBusModule;
import com.voxelgameslib.eventbus.impl.EventBusImplModule;
import com.voxelgameslib.game.GameModuleFactory;
import com.voxelgameslib.game.impl.GameImplModule;
import com.voxelgameslib.text.TextModuleFactory;
import com.voxelgameslib.text.impl.TextImplModule;
import com.voxelgameslib.user.UserModuleFactory;
import com.voxelgameslib.user.impl.UserImplModule;
import com.voxelgameslib.util.UtilModuleFactory;
import com.voxelgameslib.util.VGLInjectionPoint;
import com.voxelgameslib.util.VGLModule;
import com.voxelgameslib.util.impl.UtilImplModule;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;

public class ImplResolver {

    public static final ImplResolver INSTANCE = new ImplResolver();

    private static final Logger logger = LoggerFactory.getLogger(ImplResolver.class);

    private Map<Class, List<Module>> impls = new HashMap<>();

    private ImplResolver() {
    }

    public void registerImpl(Class clazz, Module module) {
        impls.computeIfAbsent(clazz, (key) -> new ArrayList<>()).add(module);
    }

    public void setup() {
        registerDefaultImpls();
        resolveImpls();
    }

    private void registerDefaultImpls() {
        registerImpl(EventBusModule.class, new EventBusImplModule());
        registerImpl(GameModuleFactory.class, new GameImplModule());
        registerImpl(TextModuleFactory.class, new TextImplModule());
        registerImpl(UserModuleFactory.class, new UserImplModule());
        registerImpl(UtilModuleFactory.class, new UtilImplModule());
    }

    private void resolveImpls() {
        //TODO implement me, resolve modules from file or classpath or whatever
        // maybe @OverridesModule(GameModuleFactory) and then Modules.override
        logger.info("Implement module resolving");
    }

    public Module getImpl(Class clazz) {
        List<Module> modules = impls.getOrDefault(clazz, new ArrayList<>());
        if (modules.size() == 0) {
            throw new IllegalStateException("No implementation available for module " + clazz.getName());
        }
        return modules.get(modules.size() - 1); // get last
    }

    public Collection<Module> getImpls() {
        return impls.values().stream().flatMap(List::stream).collect(Collectors.toSet());
    }
}
