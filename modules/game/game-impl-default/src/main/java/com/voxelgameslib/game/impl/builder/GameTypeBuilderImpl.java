package com.voxelgameslib.game.impl.builder;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.assistedinject.Assisted;

import com.voxelgameslib.game.GameModuleFactory;
import com.voxelgameslib.game.GameType;
import com.voxelgameslib.game.Phase;
import com.voxelgameslib.game.builder.GameTypeBuilder;
import com.voxelgameslib.game.builder.PhaseBuilder;
import com.voxelgameslib.util.GuiceFactory;
import com.voxelgameslib.util.Identifier;

public class GameTypeBuilderImpl implements GameTypeBuilder {

    private GameType gameType;
    private Identifier identifier;

    @Inject
    private GameModuleFactory gameModuleFactory;
    @Inject
    private GuiceFactory guiceFactory;

    @Inject
    GameTypeBuilderImpl(@Assisted("identifier") Identifier identifier) {
        this.identifier = identifier;
    }

    private GameType getGameType() {
        if(gameType == null) {
            gameType = gameModuleFactory.gameType(identifier);
        }
        return gameType;
    }

    @Override
    public GameType build() {
        return gameType;
    }

    @Override
    public PhaseBuilder withNewPhase(Identifier identifier) {
        Phase phase = gameModuleFactory.phase(identifier);
        return gameModuleFactory.phaseBuilder(this, phase);
    }

    @Override
    public PhaseBuilder withPhase(Class<Phase> phaseClazz) {
        return gameModuleFactory.phaseBuilder(this, guiceFactory.getInstance(phaseClazz));
    }

    @Override
    public GameTypeBuilder withPhase(Phase phase) {
        getGameType().getPhases().add(phase);
        return this;
    }
}
