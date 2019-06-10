package com.voxelgameslib.game.impl;

import com.voxelgameslib.game.GameType;
import com.voxelgameslib.game.GameTypeResolver;
import com.voxelgameslib.util.Identifier;

public class CodeGameTypeResolver implements GameTypeResolver {
    @Override
    public GameType resolve(Identifier identifier) {
        return null;
    }

    @Override
    public Identifier identifier() {
        return Identifier.ofVGL("CodeGameTypeResolver");
    }
}
