package com.voxelgameslib.dummy;

import com.vdurmont.semver4j.Semver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.voxelgameslib.core.VoxelGamesLibPlatform;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DummyPlatformTest {

    private VoxelGamesLibPlatform platform;

    @BeforeEach
    void setup() {
        platform = new DummyPlatform();
    }

    @Test
    void getImplementationName_happyday() {
        String name = platform.getImplementationName();

        assertThat(name).isNotNull();
    }

    @Test
    void getImplementationVersion_happyday() {
        Semver semver = platform.getImplementationVersion();

        assertThat(semver).isNotNull();
    }

    @Test
    void getVersion() {
    }
}
