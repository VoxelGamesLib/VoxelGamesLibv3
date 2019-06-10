package com.voxelgameslib.game;

import org.checkerframework.checker.nullness.qual.Nullable;

import com.voxelgameslib.util.Identifier;

public interface GameResolvers {

    @Nullable
    GameType loadGameType(Identifier identifier);

    @Nullable
    Feature loadFeature(Identifier identifier);

    @Nullable
    Phase loadPhase(Identifier identifier);
}
