package com.voxelgameslib.game.impl;

import com.google.common.base.MoreObjects;

import org.checkerframework.checker.nullness.qual.Nullable;

import com.voxelgameslib.game.Feature;
import com.voxelgameslib.game.GameInstance;
import com.voxelgameslib.util.Identifier;

public class FeatureImpl implements Feature {

    private Identifier identifier;
    private GameInstance gameInstance;

    @Nullable
    private Runnable init;

    public FeatureImpl(Identifier identifier) {
        this.identifier = identifier;
    }

    @Override
    public Identifier getIdentifier() {
        return identifier;
    }

    @Override
    public GameInstance getGameInstance() {
        return gameInstance;
    }

    @Override
    public void load(GameInstance gameInstance) {
        this.gameInstance = gameInstance;

        if(init != null) {
            init.run();
        }
    }

    public void init(Runnable runnable) {
        this.init = runnable;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("identifier", identifier)
                .toString();
    }
}
