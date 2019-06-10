package com.voxelgameslib.game.impl.builder;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.assistedinject.Assisted;

import com.voxelgameslib.game.GameModuleFactory;
import com.voxelgameslib.game.GameResolvers;
import com.voxelgameslib.game.GameType;
import com.voxelgameslib.game.Phase;
import com.voxelgameslib.game.builder.GameTypeBuilder;
import com.voxelgameslib.game.builder.PhaseBuilder;
import com.voxelgameslib.util.GuiceFactory;
import com.voxelgameslib.util.Identifier;

public class GameTypeBuilderImpl implements GameTypeBuilder {

    private GameType gameType;

    private GameResolvers gameResolvers;

    @Inject
    GameTypeBuilderImpl(@Assisted("identifier") Identifier identifier, GameModuleFactory gameModuleFactory, GameResolvers gameResolvers) {
        this.gameType = gameModuleFactory.gameType(identifier);
        this.gameResolvers = gameResolvers;
    }

    @Override
    public GameType build() {
        return gameType;
    }

    @Override
    public GameTypeBuilder withPhase(Identifier identifier) {
        Phase phase = gameResolvers.loadPhase(identifier);
        if(phase == null) {
            throw new IllegalArgumentException("Unknown phase " + identifier);
        }
        return withPhase(phase);
    }

    @Override
    public GameTypeBuilder withPhase(Phase phase) {
        gameType.getPhases().add(phase);
        return this;
    }
}
