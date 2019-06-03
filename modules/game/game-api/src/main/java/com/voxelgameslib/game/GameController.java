package com.voxelgameslib.game;

import com.voxelgameslib.util.Identifier;

public interface GameController {

    GameInstance startGame(GameType gameType);

    void endPhase(GameInstance gameInstance);

    void stopGame(GameInstance gameInstance);

    GameType loadGameType(Identifier test);
}
