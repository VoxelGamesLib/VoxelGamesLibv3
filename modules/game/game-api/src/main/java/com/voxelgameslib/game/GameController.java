package com.voxelgameslib.game;

import org.checkerframework.checker.nullness.qual.Nullable;

import com.voxelgameslib.util.Identifier;

public interface GameController {

    GameInstance startGame(GameType gameType);

    void endPhase(GameInstance gameInstance);

    void stopGame(GameInstance gameInstance);

    @Nullable
    GameType loadGameType(Identifier test);
}
