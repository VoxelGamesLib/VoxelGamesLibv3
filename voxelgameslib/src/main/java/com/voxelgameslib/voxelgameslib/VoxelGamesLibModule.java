package com.voxelgameslib.voxelgameslib;

import com.google.common.collect.Comparators;
import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.util.Modules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.voxelgameslib.ImplResolver;
import com.voxelgameslib.util.GuiceFactory;
import com.voxelgameslib.util.Identifier;
import com.voxelgameslib.util.ModuleFactory;
import com.voxelgameslib.util.VGLInjectionPoint;
import com.voxelgameslib.util.VGLModule;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;

public class VoxelGamesLibModule implements Module {

    private static final Logger logger = LoggerFactory.getLogger(VoxelGamesLibModule.class);

    @Override
    public void configure(Binder binder) {
        staticInjection(binder);
    }

    private void staticInjection(Binder binder) {
        try (ScanResult scanResult = new ClassGraph().enableClassInfo().scan()) {
            scanResult.getClassesImplementing(VGLInjectionPoint.class.getName()).stream()
                    .map(ClassInfo::loadClass).forEach(types -> {
                binder.requestStaticInjection(types);
                if (logger.isDebugEnabled()) {
                    logger.debug("Statically inject {}", types);
                }
            });
        }
    }

    @Provides
    public Logger logger() {
        return LoggerFactory.getLogger(this.getClass()); //TODO auto inject loggers
    }

    public static Injector createInjector() {
        List<Module> modules = new ArrayList<>();

        modules.add(new VoxelGamesLibModule());

        for (Class<? extends ModuleFactory> module : ImplResolver.INSTANCE.getModules()) {
            Collection<Class<? extends VGLModule>> impls = ImplResolver.INSTANCE.getImpls(module);

            if (impls.size() == 1) {
                VGLModule impl = newInstance(impls.stream().findFirst().get());
                modules.add(impl);

                if (logger.isDebugEnabled()) {
                    logger.debug("Binding implementation {} to module {}", impl.identifier(), module.getName());
                }
            } else {
                List<VGLModule> vglModules = impls.stream().map(VoxelGamesLibModule::newInstance).collect(Collectors.toList());
                Module combinedModule = vglModules.get(0);
                for (int i = 1; i < vglModules.size(); i++) {
                    combinedModule = Modules.override(combinedModule).with(vglModules.get(i));
                }
                modules.add(combinedModule);

                if (logger.isDebugEnabled()) {
                    String names = vglModules.stream().map(VGLModule::identifier).map(Identifier::toString)
                            .collect(Collectors.joining(" -> "));
                    logger.debug("Binding implementations {} to modules {}", names, module.getName());
                }
            }
        }

        // remove fails
        modules = modules.stream().filter(Objects::nonNull).collect(Collectors.toList());

        if (logger.isDebugEnabled()) {
            logger.debug("Creating injector with modules: {}",
                    modules.stream().map(module -> module.getClass().getPackageName().startsWith("com.voxelgameslib")
                            ? "vgl." + module.getClass().getSimpleName()
                            : module.getClass().getName())
                            .sorted()
                            .collect(Collectors.joining(", ")));
        }

        Injector injector = Guice.createInjector(modules);
        injector.getInstance(GuiceFactory.class).setInjector(injector);
        return injector;
    }

    private static VGLModule newInstance(Class<? extends VGLModule> impl) {
        try {
            return (VGLModule) impl.getConstructors()[0].newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            logger.error(String.format("Can't instanciate module %s", impl.getName()), e);
            return null;
        }
    }
}
