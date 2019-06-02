package com.voxelgameslib.dummy;

import com.vdurmont.semver4j.Semver;

import com.voxelgameslib.core.VoxelGamesLibPlatform;

public class DummyPlatform implements VoxelGamesLibPlatform {

    @Override
    public String getImplementationName() {
        return "Dummy";
    }

    @Override
    public Semver getImplementationVersion() {
        return new Semver("3.0.0-SNAPSHOT");
    }

    @Override
    public Semver getVersion() {
        return new Semver("3.0.0-SNAPSHOT");
    }
}
