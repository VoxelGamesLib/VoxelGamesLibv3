package com.voxelgameslib.game.events;

import com.voxelgameslib.game.Feature;

public class FeatureStartEvent {

    private Feature feature;

    public FeatureStartEvent(Feature feature) {
        this.feature = feature;
    }

    public Feature getFeature() {
        return feature;
    }
}
