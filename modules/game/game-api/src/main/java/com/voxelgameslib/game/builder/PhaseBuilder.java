package com.voxelgameslib.game.builder;

import java.util.function.Consumer;

import com.voxelgameslib.game.Feature;

public interface PhaseBuilder {

    GameTypeBuilder build();

    <T extends Feature> PhaseBuilder withFeature(Class<T> featureClass);

    <T extends Feature> PhaseBuilder withFeature(Class<T> featureClass, Consumer<T> featureConfigurator);
}
