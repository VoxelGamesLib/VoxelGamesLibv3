package com.voxelgameslib.game.impl.builder;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import java.util.function.Consumer;

import com.voxelgameslib.game.Feature;
import com.voxelgameslib.game.Phase;
import com.voxelgameslib.game.builder.GameTypeBuilder;
import com.voxelgameslib.game.builder.PhaseBuilder;
import com.voxelgameslib.util.GuiceFactory;

public class PhaseBuilderImpl implements PhaseBuilder {

    private GameTypeBuilder gameTypeBuilder;
    private Phase phase;

    @Inject
    private GuiceFactory guiceFactory;

    @Inject
    PhaseBuilderImpl(@Assisted("gameTypeBuilder") GameTypeBuilder gameTypeBuilder, @Assisted("phase") Phase phase) {
        this.gameTypeBuilder = gameTypeBuilder;
        this.phase = phase;
    }

    @Override
    public GameTypeBuilder build() {
        return gameTypeBuilder.withPhase(phase);
    }

    @Override
    public <T extends Feature> PhaseBuilder withFeature(Class<T> featureClass) {
        Feature feature = guiceFactory.getInstance(featureClass);
        phase.getFeatures().add(feature);
        return this;
    }

    @Override
    public <T extends Feature> PhaseBuilder withFeature(Class<T> featureClass, Consumer<T> featureConfigurator) {
        T feature = guiceFactory.getInstance(featureClass);
        featureConfigurator.accept(feature);
        phase.getFeatures().add(feature);
        return this;
    }
}
