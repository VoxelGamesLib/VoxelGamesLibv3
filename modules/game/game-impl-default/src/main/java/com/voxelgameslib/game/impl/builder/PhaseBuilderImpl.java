package com.voxelgameslib.game.impl.builder;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.function.Consumer;

import com.voxelgameslib.game.Feature;
import com.voxelgameslib.game.GameModuleFactory;
import com.voxelgameslib.game.GameResolvers;
import com.voxelgameslib.game.Phase;
import com.voxelgameslib.game.builder.PhaseBuilder;
import com.voxelgameslib.util.Identifier;

public class PhaseBuilderImpl implements PhaseBuilder {

    private GameResolvers gameResolvers;

    private Phase phase;

    @Inject
    PhaseBuilderImpl(@Assisted("identifier") Identifier identifier, GameModuleFactory moduleFactory, GameResolvers gameResolvers) {
        this.phase = moduleFactory.phase(identifier);
        this.gameResolvers = gameResolvers;
    }

    @Override
    public Phase build() {
        return phase;
    }

    @Override
    public <T extends Feature> PhaseBuilder withFeature(Identifier identifier) {
        return withFeature(identifier, null);
    }

    @Override
    public <T extends Feature> PhaseBuilder withFeature(Identifier identifier, @Nullable Consumer<T> featureConfigurator) {
        T feature = (T) gameResolvers.loadFeature(identifier);
        if (feature == null) {
            throw new IllegalArgumentException("Unknown feature " + identifier);
        }
        if (featureConfigurator != null) {
            featureConfigurator.accept(feature);
        }

        phase.getFeatures().add(feature);

        return this;
    }
}
