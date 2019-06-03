package com.voxelgameslib.util.impl;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import com.voxelgameslib.util.Identifier;
import com.voxelgameslib.util.UtilModule;

public class UtilModuleImpl implements Module {

    @Override
    public void configure(Binder binder) {
        binder.install(new FactoryModuleBuilder()
                .implement(Identifier.class, IdentifierImpl.class)
                .build(UtilModule.class));
    }
}
