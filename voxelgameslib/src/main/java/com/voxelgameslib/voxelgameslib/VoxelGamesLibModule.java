package com.voxelgameslib.voxelgameslib;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Module;

import com.voxelgameslib.util.UtilModule;

public class VoxelGamesLibModule implements Module {

    @Override
    public void configure(Binder binder) {

    }

    public static void createInjector() {
        Guice.createInjector(new VoxelGamesLibModule(),
                new UtilModule());
    }
}
