package com.voxelgameslib.core;

import com.vdurmont.semver4j.Semver;

public interface VoxelGamesLibPlatform {

    String getImplementationName();

    Semver getImplementationVersion();

    Semver getVersion();
}
