package com.voxelgameslib.game;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public interface GameModuleFactory {

    @Inject
    GameInstance gameInstance(@Assisted("gameType") GameType gameType);
}
