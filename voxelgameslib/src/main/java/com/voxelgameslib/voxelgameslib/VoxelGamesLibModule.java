package com.voxelgameslib.voxelgameslib;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Provides;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.voxelgameslib.ImplResolver;
import com.voxelgameslib.text.Text;
import com.voxelgameslib.util.GuiceFactory;
import com.voxelgameslib.util.Identifier;
import com.voxelgameslib.util.VGLInjectionPoint;

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
                    .map(ClassInfo::loadClass).forEach(binder::requestStaticInjection);
        }
    }

    @Provides
    public Logger logger() {
        return LoggerFactory.getLogger(this.getClass()); //TODO auto inject loggers
    }

    public static Injector createInjector() {
        List<Module> modules = new ArrayList<>(ImplResolver.INSTANCE.getImpls());
        modules.add(new VoxelGamesLibModule());

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
}
