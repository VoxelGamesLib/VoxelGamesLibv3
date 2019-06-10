package com.voxelgameslib.game;

import org.checkerframework.checker.nullness.qual.Nullable;

import com.voxelgameslib.util.API;
import com.voxelgameslib.util.Identifier;

public interface GameTypeResolver extends API {

    @Nullable
    GameType resolve(Identifier identifier);
}
