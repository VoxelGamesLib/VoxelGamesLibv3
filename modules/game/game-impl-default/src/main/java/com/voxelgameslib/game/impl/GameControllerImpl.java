package com.voxelgameslib.game.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import org.slf4j.Logger;

import com.voxelgameslib.game.GameController;
import com.voxelgameslib.game.GameInstance;
import com.voxelgameslib.game.GameModuleFactory;
import com.voxelgameslib.game.GameType;
import com.voxelgameslib.util.Identifier;

@Singleton
public class GameControllerImpl implements GameController {

    @Inject
    private Logger logger;
    @Inject
    private GameModuleFactory gameModuleFactory;

    @Override
    public GameInstance startGame(GameType gameType) {
        GameInstance gameInstance = gameModuleFactory.gameInstance(gameType);
        gameInstance.setGameType(gameType);

        return gameInstance;
    }

    @Override
    public void endPhase(GameInstance gameInstance) {

    }

    @Override
    public void stopGame(GameInstance gameInstance) {

    }

    @Override
    public GameType loadGameType(Identifier identifier) {
        logger.info("load game type " + identifier);
        return null;
    }
}
