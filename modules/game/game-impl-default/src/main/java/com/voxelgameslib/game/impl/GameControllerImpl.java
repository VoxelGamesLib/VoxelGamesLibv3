package com.voxelgameslib.game.impl;

import com.google.inject.Inject;

import org.slf4j.Logger;

import java.util.ServiceLoader;

import com.voxelgameslib.game.GameController;
import com.voxelgameslib.game.GameInstance;
import com.voxelgameslib.game.GameType;
import com.voxelgameslib.util.Identifier;

public class GameControllerImpl implements GameController {

    @Inject
    private Logger logger;

    @Override
    public GameInstance startGame(GameType gameType) {
        GameInstance gameInstance = ServiceLoader.load(GameInstance.class).findFirst().orElseThrow();
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
