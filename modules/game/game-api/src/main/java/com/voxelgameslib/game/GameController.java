package com.voxelgameslib.game;

public interface GameController {

    GameInstance startGame(GameType gameType);

    void startPhase(GameInstance gameInstance, Phase phase);

    void endPhase(GameInstance gameInstance);

    void stopGame(GameInstance gameInstance);
}
