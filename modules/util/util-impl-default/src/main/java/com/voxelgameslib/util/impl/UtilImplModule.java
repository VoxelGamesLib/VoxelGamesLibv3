package com.voxelgameslib.util.impl;

import com.google.inject.Binder;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import com.voxelgameslib.util.Identifier;
import com.voxelgameslib.util.UtilModuleFactory;
import com.voxelgameslib.util.VGLModule;

public class UtilImplModule implements VGLModule {

    @Override
    public void configure(Binder binder) {
        binder.install(new FactoryModuleBuilder()
                .implement(Identifier.class, IdentifierImpl.class)
                .build(UtilModuleFactory.class));
    }

    @Override
    public Identifier identifier() {
        return Identifier.ofVGL("DefaultUtilModule");
    }
}
