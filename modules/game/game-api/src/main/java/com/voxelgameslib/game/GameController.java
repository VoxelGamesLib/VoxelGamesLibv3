package com.voxelgameslib.game;

public interface GameController {

    GameInstance startGame(GameType gameType);

    void endPhase(GameInstance gameInstance);

    void stopGame(GameInstance gameInstance);
}
