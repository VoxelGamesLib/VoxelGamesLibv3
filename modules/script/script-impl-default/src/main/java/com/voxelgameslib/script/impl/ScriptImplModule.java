package com.voxelgameslib.script.impl;

import com.google.inject.Binder;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import com.voxelgameslib.script.ScriptController;
import com.voxelgameslib.script.ScriptModuleFactory;
import com.voxelgameslib.util.Identifier;
import com.voxelgameslib.util.ImplementsModule;
import com.voxelgameslib.util.VGLModule;

@ImplementsModule(value = ScriptModuleFactory.class, prio = ImplementsModule.VGL_PRIO)
public class ScriptImplModule implements VGLModule {

    @Override
    public void configure(Binder binder) {
        binder.bind(ScriptController.class).to(ScriptControllerImpl.class);

        binder.install(new FactoryModuleBuilder()
                .implement(ScriptController.class, ScriptControllerImpl.class)
                .build(ScriptModuleFactory.class));
    }

    @Override
    public Identifier identifier() {
        return Identifier.ofVGL("DefaultTextModule");
    }
}
