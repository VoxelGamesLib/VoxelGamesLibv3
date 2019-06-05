package com.voxelgameslib.voxelgameslib;

import com.google.inject.Injector;

import org.slf4j.LoggerFactory;

import java.util.UUID;

import com.voxelgameslib.ImplResolver;
import com.voxelgameslib.game.GameController;
import com.voxelgameslib.game.GameType;
import com.voxelgameslib.text.Text;
import com.voxelgameslib.user.User;
import com.voxelgameslib.user.UserModuleFactory;
import com.voxelgameslib.util.Identifier;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

public class Test {

    public static void main(String[] args) {
        Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        root.setLevel(Level.DEBUG);

        // always need to call this first
        ImplResolver.INSTANCE.setup();

        Injector injector = VoxelGamesLibModule.createInjector();

        Identifier t = Identifier.ofVGL("test");
        System.out.println(t);

        UserModuleFactory userModuleFactory = injector.getInstance(UserModuleFactory.class);
        User user = userModuleFactory.user(UUID.randomUUID(), "username");

        System.out.println(user);

        GameController gameController = injector.getInstance(GameController.class);
        GameType gameType = gameController.loadGameType(Identifier.ofVGL("test"));
        gameController.startGame(gameType);
    }

}
