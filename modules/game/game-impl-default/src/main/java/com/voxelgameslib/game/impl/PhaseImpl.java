package com.voxelgameslib.game.impl;

import com.google.common.base.MoreObjects;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import java.util.HashSet;
import java.util.Set;

import com.voxelgameslib.game.Feature;
import com.voxelgameslib.game.Phase;
import com.voxelgameslib.util.Identifier;

public class PhaseImpl implements Phase {

    private Identifier identifier;
    private Set<Feature> features;

    @Inject
    PhaseImpl(@Assisted("identifier") Identifier identifier) {
        this.identifier = identifier;
        features = new HashSet<>();
    }

    @Override
    public Identifier getIdentifier() {
        return identifier;
    }

    @Override
    public Set<Feature> getFeatures() {
        return features;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("identifier", identifier)
                .add("features", features)
                .toString();
    }
}
