package com.voxelgameslib.text.impl;

import com.google.inject.Binder;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import com.voxelgameslib.text.Text;
import com.voxelgameslib.text.TextModuleFactory;
import com.voxelgameslib.util.Identifier;
import com.voxelgameslib.util.VGLModule;

public class TextImplModule implements VGLModule {

    @Override
    public void configure(Binder binder) {
        binder.install(new FactoryModuleBuilder()
                .implement(Text.class, TextImpl.class)
                .build(TextModuleFactory.class));
    }

    @Override
    public Identifier identifier() {
        return Identifier.ofVGL("DefaultTextModule");
    }
}
