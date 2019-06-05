package com.voxelgameslib.game;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import com.voxelgameslib.util.ModuleFactory;

public interface GameModuleFactory extends ModuleFactory {

    @Inject
    GameInstance gameInstance(@Assisted("gameType") GameType gameType);
}
