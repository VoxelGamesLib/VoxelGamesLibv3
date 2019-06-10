package com.voxelgameslib.game;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import java.util.UUID;

import com.voxelgameslib.game.builder.GameTypeBuilder;
import com.voxelgameslib.game.builder.PhaseBuilder;
import com.voxelgameslib.util.Identifier;
import com.voxelgameslib.util.ModuleFactory;

public interface GameModuleFactory extends ModuleFactory {

    @Inject
    GameInstance gameInstance(@Assisted("gameType") GameType gameType, @Assisted("uuid") UUID id);

    @Inject
    GameTypeBuilder gameTypeBuilder(@Assisted("identifier") Identifier identifier);

    @Inject
    PhaseBuilder phaseBuilder(@Assisted("identifier") Identifier identifier);

    @Inject
    GameType gameType(@Assisted("identifier") Identifier identifier);

    @Inject
    Phase phase(@Assisted("identifier") Identifier identifier);

//    @Inject
//    Feature feature(@Assisted("identifier") Identifier identifier);
}
