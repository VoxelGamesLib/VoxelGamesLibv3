package com.voxelgameslib.voxelgameslib;

import java.util.ServiceLoader;

import com.voxelgameslib.game.Feature;
import com.voxelgameslib.game.Game;

public class Test {

    public static void main(String[] args) {
        Game game = ServiceLoader.load(Game.class).findFirst().orElseThrow();
        System.out.println("found " + game);
        Feature feature = ServiceLoader.load(Feature.class).findFirst().orElseThrow();
        System.out.println("feature " + feature);
    }
}
