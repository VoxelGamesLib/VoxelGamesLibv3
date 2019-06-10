package com.voxelgameslib.game;

import com.voxelgameslib.util.Identifier;

public interface Feature {

    Identifier getIdentifier();

    GameInstance getGameInstance();

    void load(GameInstance gameInstance);
}
