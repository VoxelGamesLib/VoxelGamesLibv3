package com.voxelgameslib.game.impl;

import com.google.inject.Binder;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import com.voxelgameslib.game.GameController;
import com.voxelgameslib.game.GameInstance;
import com.voxelgameslib.game.GameModuleFactory;
import com.voxelgameslib.game.GameType;
import com.voxelgameslib.util.Identifier;
import com.voxelgameslib.util.ImplementsModule;
import com.voxelgameslib.util.VGLModule;

@ImplementsModule(value = GameModuleFactory.class, prio = ImplementsModule.VGL_PRIO)
public class GameImplModule implements VGLModule {

    @Override
    public void configure(Binder binder) {
        binder.bind(GameController.class).to(GameControllerImpl.class);
        binder.bind(GameInstance.class).to(GameInstanceImpl.class);
        binder.bind(GameType.class).to(GameTypeImpl.class);

        binder.install(new FactoryModuleBuilder()
                .implement(GameInstance.class, GameInstanceImpl.class)
                .build(GameModuleFactory.class));
    }

    @Override
    public Identifier identifier() {
        return Identifier.ofVGL("DefaultGameModule");
    }
}
