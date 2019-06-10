package com.voxelgameslib.game.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import org.slf4j.Logger;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.voxelgameslib.game.GameController;
import com.voxelgameslib.game.GameInjectionPoint;
import com.voxelgameslib.game.GameInstance;
import com.voxelgameslib.game.GameModuleFactory;
import com.voxelgameslib.game.GameType;
import com.voxelgameslib.game.GameTypeResolver;
import com.voxelgameslib.util.Identifier;

@Singleton
public class GameControllerImpl implements GameController {

    @Inject
    private Logger logger;
    @Inject
    private GameModuleFactory gameModuleFactory;
    @Inject
    private Set<GameTypeResolver> gameTypeResolvers;

    @Override
    public GameInstance startGame(GameType gameType) {
        GameInstance gameInstance = gameModuleFactory.gameInstance(gameType, UUID.randomUUID());

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
        return gameTypeResolvers.stream()
                .map(gameTypeResolver -> gameTypeResolver.resolve(identifier)).
                filter(Objects::nonNull)
                .findFirst().orElse(null);
    }
}
