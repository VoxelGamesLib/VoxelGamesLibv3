package com.voxelgameslib.text.testimpl;

import com.google.inject.Binder;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import com.voxelgameslib.text.Text;
import com.voxelgameslib.text.TextModuleFactory;
import com.voxelgameslib.util.Identifier;
import com.voxelgameslib.util.ImplementsModule;
import com.voxelgameslib.util.VGLModule;

@ImplementsModule(TextModuleFactory.class)
public class TextTestImplModule implements VGLModule {

    @Override
    public void configure(Binder binder) {
        binder.install(new FactoryModuleBuilder()
                .implement(Text.class, TextTestImpl.class)
                .build(TextModuleFactory.class));
    }

    @Override
    public Identifier identifier() {
        return Identifier.ofVGL("TestTextModule");
    }
}
