package com.voxelgameslib.voxelgameslib.test;

import com.google.inject.Injector;

import org.slf4j.LoggerFactory;

import java.util.UUID;

import com.voxelgameslib.ImplResolver;
import com.voxelgameslib.game.GameController;
import com.voxelgameslib.game.GameType;
import com.voxelgameslib.game.builder.GameTypeBuilder;
import com.voxelgameslib.script.ScriptController;
import com.voxelgameslib.text.Text;
import com.voxelgameslib.user.User;
import com.voxelgameslib.user.UserModuleFactory;
import com.voxelgameslib.util.Identifier;
import com.voxelgameslib.voxelgameslib.VoxelGamesLibModule;

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

        System.out.println(Text.ofPlain("test"));

        GameType type = GameTypeBuilder.of(Identifier.ofVGL("TestGameType"))
                .withNewPhase(Identifier.ofVGL("TestPhase"))
                .withFeature(TestFeature.class)
                .withFeature(TestFeature.class, (feature) -> feature.setTest("test"))
                .build()
                .build();
        System.out.println(type);

        injector.getInstance(ScriptController.class).executeScript("js", "print('test2')");
        GameType gameType2 = injector.getInstance(ScriptController.class).executeScript("js", Test.class.getResource("/test.js"));
        System.out.println(gameType2);

//        GameType gameType3 = injector.getInstance(ScriptController.class).executeScript("ruby", Test.class.getResource("/test.rb"));
//        System.out.println(gameType3);
//
//        GameType gameType4 = injector.getInstance(ScriptController.class).executeScript("python", Test.class.getResource("/test.py"));
//        System.out.println(gameType4);
    }
}
