package com.voxelgameslib.game;

import org.checkerframework.checker.nullness.qual.Nullable;

import com.voxelgameslib.util.API;
import com.voxelgameslib.util.Identifier;

public interface PhaseResolver extends API {

    @Nullable
    Phase resolve(Identifier identifier);
}
