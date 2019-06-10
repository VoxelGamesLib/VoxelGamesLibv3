package com.voxelgameslib.game.builder;

import java.util.function.Consumer;

import com.voxelgameslib.game.Feature;
import com.voxelgameslib.game.GameInjectionPoint;
import com.voxelgameslib.game.Phase;
import com.voxelgameslib.util.Identifier;

public interface PhaseBuilder {

    Phase build();

    <T extends Feature> PhaseBuilder withFeature(Identifier identifier);

    <T extends Feature> PhaseBuilder withFeature(Identifier identifier, Consumer<T> featureConfigurator);

    static PhaseBuilder of(Identifier identifier) {
        return GameInjectionPoint.gameModuleFactory.phaseBuilder(identifier);
    }
}
