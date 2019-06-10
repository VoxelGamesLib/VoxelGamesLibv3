package com.voxelgameslib.game.impl;

import com.google.inject.Inject;

import java.util.Objects;
import java.util.Set;

import com.voxelgameslib.game.Feature;
import com.voxelgameslib.game.FeatureResolver;
import com.voxelgameslib.game.GameResolvers;
import com.voxelgameslib.game.GameType;
import com.voxelgameslib.game.GameTypeResolver;
import com.voxelgameslib.game.Phase;
import com.voxelgameslib.game.PhaseResolver;
import com.voxelgameslib.util.Identifier;

public class GameResolversImpl implements GameResolvers {

    @Inject
    private Set<GameTypeResolver> gameTypeResolvers;
    @Inject
    private Set<FeatureResolver> featureResolvers;
    @Inject
    private Set<PhaseResolver> phaseResolvers;

    @Override
    public GameType loadGameType(Identifier identifier) {
        return gameTypeResolvers.stream()
                .map(gameTypeResolver -> gameTypeResolver.resolve(identifier)).
                        filter(Objects::nonNull)
                .findFirst().orElse(null);
    }

    @Override
    public Feature loadFeature(Identifier identifier) {
        return featureResolvers.stream()
                .map(featureResolver -> featureResolver.resolve(identifier)).
                        filter(Objects::nonNull)
                .findFirst().orElse(null);
    }

    @Override
    public Phase loadPhase(Identifier identifier) {
        return phaseResolvers.stream()
                .map(phaseResolver -> phaseResolver.resolve(identifier)).
                        filter(Objects::nonNull)
                .findFirst().orElse(null);
    }

}
