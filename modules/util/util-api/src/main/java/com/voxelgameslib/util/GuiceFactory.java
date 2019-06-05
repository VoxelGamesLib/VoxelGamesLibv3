package com.voxelgameslib.util;

import com.google.inject.Injector;
import com.google.inject.Singleton;

@Singleton
public class GuiceFactory {

    private Injector injector;

    public void setInjector(Injector injector) {
        this.injector = injector;
    }

    public <T> T getInstance(Class<T> aClass) {
        return injector.getInstance(aClass);
    }
}
