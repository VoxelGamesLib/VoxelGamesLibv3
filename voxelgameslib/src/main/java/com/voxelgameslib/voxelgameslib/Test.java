package com.voxelgameslib.voxelgameslib;

import java.util.ServiceLoader;

import com.voxelgameslib.game.GameController;
import com.voxelgameslib.game.GameType;
import com.voxelgameslib.util.Identifier;

public class Test {

    public static void main(String[] args) {

        GameType gameType = gameController.loadGameType(Identifier.ofVGL("test"));

        gameController.startGame(gameType);
    }
}
