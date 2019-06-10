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
import com.voxelgameslib.game.events.FeatureStartEvent;
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
        logger.debug("Starting a new instance of {} with UUID {}", gameInstance.getGameType(), gameInstance.getId());
        startPhase(gameInstance, gameType.getPhases().get(0));
        return gameInstance;
    }

    @Override
    public void startPhase(GameInstance gameInstance, Phase phase) {
        logger.debug("Starting phase {} for game {}", phase, gameInstance);
        gameInstance.setActivePhase(phase);
        // loading
        phase.getFeatures().forEach(f -> {
            logger.debug("Loading feature {} for game {}", f, gameInstance);
            f.load(gameInstance);
        });
        // starting
        phase.getFeatures().forEach(f -> {
            logger.debug("Staring feature {} for game {}", f, gameInstance);
            gameInstance.getEventBus().post(new FeatureStartEvent(f));
        });
    }

    @Override
    public void endPhase(GameInstance gameInstance) {

    }

    @Override
    public void stopGame(GameInstance gameInstance) {

    }
}
