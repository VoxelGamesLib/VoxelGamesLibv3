package com.voxelgameslib.game.impl;

import com.google.inject.Binder;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;

import java.io.File;

import com.voxelgameslib.game.GameController;
import com.voxelgameslib.game.GameInstance;
import com.voxelgameslib.game.GameModuleFactory;
import com.voxelgameslib.game.GameType;
import com.voxelgameslib.game.GameTypeResolver;
import com.voxelgameslib.game.Phase;
import com.voxelgameslib.game.builder.GameTypeBuilder;
import com.voxelgameslib.game.builder.PhaseBuilder;
import com.voxelgameslib.game.impl.builder.GameTypeBuilderImpl;
import com.voxelgameslib.game.impl.builder.PhaseBuilderImpl;
import com.voxelgameslib.util.Identifier;
import com.voxelgameslib.util.ImplementsModule;
import com.voxelgameslib.util.VGLModule;

@ImplementsModule(value = GameModuleFactory.class, prio = ImplementsModule.VGL_PRIO)
public class GameImplModule implements VGLModule {

    @Override
    public void configure(Binder binder) {
        binder.bind(GameController.class).to(GameControllerImpl.class);

        binder.install(new FactoryModuleBuilder()
                .implement(GameInstance.class, GameInstanceImpl.class)
                .implement(GameTypeBuilder.class, GameTypeBuilderImpl.class)
                .implement(PhaseBuilder.class, PhaseBuilderImpl.class)
                .implement(GameType.class, GameTypeImpl.class)
                .implement(Phase.class, PhaseImpl.class)
                .build(GameModuleFactory.class));

        File dataFolder = new File(".");
        binder.bind(File.class).annotatedWith(Names.named("gameTypeScriptFolder")).toInstance(new File(dataFolder, "gametypes"));

        Multibinder<GameTypeResolver> gameTypeResolverBinder = Multibinder.newSetBinder(binder, GameTypeResolver.class);
        gameTypeResolverBinder.addBinding().to(ScriptGameTypeResolver.class);
        gameTypeResolverBinder.addBinding().to(CodeGameTypeResolver.class);
    }

    @Override
    public Identifier identifier() {
        return Identifier.ofVGL("DefaultGameModule");
    }
}
