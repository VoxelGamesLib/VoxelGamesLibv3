package com.voxelgameslib.game.scriptresolver;

import com.google.inject.Binder;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;

import java.io.File;

import com.voxelgameslib.game.FeatureResolver;
import com.voxelgameslib.game.GameModuleFactory;
import com.voxelgameslib.game.GameTypeResolver;
import com.voxelgameslib.game.PhaseResolver;
import com.voxelgameslib.util.Identifier;
import com.voxelgameslib.util.ImplementsModule;
import com.voxelgameslib.util.VGLModule;

@ImplementsModule(value = GameModuleFactory.class, prio = ImplementsModule.VGL_PRIO + 1)
public class GameScriptResolverModule implements VGLModule {

    @Override
    public Identifier identifier() {
        return Identifier.ofVGL("GameScriptResolverModule");
    }

    @Override
    public void configure(Binder binder) {
        File root = new File(".");
        File dataFolder = new File(root, "data");
        binder.bind(File.class).annotatedWith(Names.named("gameTypeScriptFolder")).toInstance(new File(dataFolder, "gametypes"));
        binder.bind(File.class).annotatedWith(Names.named("featureScriptFolder")).toInstance(new File(dataFolder, "features"));
        binder.bind(File.class).annotatedWith(Names.named("phaseScriptFolder")).toInstance(new File(dataFolder, "phases"));

        Multibinder<GameTypeResolver> gameTypeResolverBinder = Multibinder.newSetBinder(binder, GameTypeResolver.class);
        gameTypeResolverBinder.addBinding().to(ScriptGameTypeResolver.class);

        Multibinder<FeatureResolver> featureResolverBinder = Multibinder.newSetBinder(binder, FeatureResolver.class);
        featureResolverBinder.addBinding().to(ScriptFeatureResolver.class);

        Multibinder<PhaseResolver> phaseResolverBinder = Multibinder.newSetBinder(binder, PhaseResolver.class);
        phaseResolverBinder.addBinding().to(ScriptPhaseResolver.class);
    }
}
