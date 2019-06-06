package com.voxelgameslib.game;

import java.util.Set;

import com.voxelgameslib.util.Identifier;

public interface Phase {

    Identifier getIdentifier();

    Set<Feature> getFeatures();
}
