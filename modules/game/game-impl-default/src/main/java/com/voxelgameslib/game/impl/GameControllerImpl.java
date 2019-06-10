package com.voxelgameslib.game.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import org.slf4j.Logger;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.voxelgameslib.game.Feature;
import com.voxelgameslib.game.GameController;
import com.voxelgameslib.game.GameInjectionPoint;
import com.voxelgameslib.game.GameInstance;
import com.voxelgameslib.game.GameModuleFactory;
import com.voxelgameslib.game.GameType;
import com.voxelgameslib.game.GameTypeResolver;
import com.voxelgameslib.game.Phase;
import com.voxelgameslib.util.Identifier;

@Singleton
public class GameControllerImpl implements GameController {

    @Inject
    private Logger logger;
    @Inject
    private GameModuleFactory gameModuleFactory;

    @Override
    public GameInstance startGame(GameType gameType) {
        GameInstance gameInstance = gameModuleFactory.gameInstance(gameType, UUID.randomUUID());
        startPhase(gameInstance, gameType.getPhases().get(0));
        return gameInstance;
    }

    @Override
    public void startPhase(GameInstance gameInstance, Phase phase) {
        gameInstance.setActivePhase(phase);
        phase.getFeatures().forEach(f -> f.load(gameInstance));
    }

    @Override
    public void endPhase(GameInstance gameInstance) {

    }

    @Override
    public void stopGame(GameInstance gameInstance) {

    }
}
