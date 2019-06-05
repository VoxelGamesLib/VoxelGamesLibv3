package com.voxelgameslib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import com.voxelgameslib.util.ImplementsModule;
import com.voxelgameslib.util.ModuleFactory;
import com.voxelgameslib.util.VGLModule;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;

public class ImplResolver {

    public static final ImplResolver INSTANCE = new ImplResolver();

    private static final Logger logger = LoggerFactory.getLogger(ImplResolver.class);

    private Map<Class<? extends ModuleFactory>, TreeMap<Integer, Class<? extends VGLModule>>> impls = new HashMap<>();

    private ImplResolver() {
    }

    public void setup() {
        try (ScanResult scanResult = new ClassGraph().enableClassInfo().scan()) {
            scanResult.getClassesImplementing(VGLModule.class.getName()).stream()
                    .map(ClassInfo::loadClass).forEach(moduleClass -> {
                ImplementsModule moduleInfo = moduleClass.getAnnotation(ImplementsModule.class);
                if (moduleInfo == null) {
                    logger.error("Module {} doesn't specify a @ImplementsModule annotation!", moduleClass.getName());
                } else {
                    //noinspection unchecked
                    registerImpl(moduleInfo.value(), (Class<? extends VGLModule>) moduleClass, moduleInfo.prio());
                }
            });
        }

        logger.info("Found {} modules with {} implementations",
                impls.size(), impls.values().stream().mapToLong(TreeMap::size).sum());
    }

    private void registerImpl(Class<? extends ModuleFactory> api, Class<? extends VGLModule> impl, int prio) {
        impls.computeIfAbsent(api, (key) -> new TreeMap<>()).put(prio, impl);
        if (logger.isDebugEnabled()) {
            logger.debug("Registering implementation {} of module {}", impl.getName(), api.getName());
        }
    }

    public Collection<Class<? extends VGLModule>> getImpls(Class<? extends ModuleFactory> clazz) {
        Collection<Class<? extends VGLModule>> modules = impls.getOrDefault(clazz, new TreeMap<>()).values();
        if (modules.size() == 0) {
            throw new IllegalStateException("No implementation available for module " + clazz.getName());
        }
        return modules;
    }

    public Set<Class<? extends ModuleFactory>> getModules() {
        return impls.keySet();
    }
}
