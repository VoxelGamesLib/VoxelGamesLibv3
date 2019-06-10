package com.voxelgameslib.game.impl;

import com.voxelgameslib.game.Feature;
import com.voxelgameslib.util.Identifier;

public class FeatureImpl implements Feature {

    private Identifier identifier;

    public FeatureImpl(Identifier identifier) {
        this.identifier = identifier;
    }

    @Override
    public Identifier getIdentifier() {
        return identifier;
    }
}
