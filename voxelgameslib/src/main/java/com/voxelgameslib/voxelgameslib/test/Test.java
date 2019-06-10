package com.voxelgameslib.voxelgameslib.test;

import com.google.inject.Inject;
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
        Test test = new Test();
        injector.injectMembers(test);
        test.test();
    }

    @Inject
    private UserModuleFactory userModuleFactory;
    @Inject
    private GameController gameController;
    @Inject
    private ScriptController scriptController;

    private void test() {
        Identifier t = Identifier.ofVGL("test");
        System.out.println(t);

        User user = userModuleFactory.user(UUID.randomUUID(), "username");

        System.out.println(user);

        GameType gameType = gameController.loadGameType(Identifier.ofVGL("TestGameType"));
        if (gameType != null) {
            System.out.println("loaded gametype " + gameType + " from script!");
            gameController.startGame(gameType);
        }

        System.out.println(Text.ofPlain("test"));

        GameType type = GameTypeBuilder.of(Identifier.ofVGL("TestGameType"))
                .withNewPhase(Identifier.ofVGL("TestPhase"))
                .withFeature(TestFeature.class)
                .withFeature(TestFeature.class, (feature) -> feature.setTest("test"))
                .build()
                .build();
        System.out.println(type);

        scriptController.executeScript("js", "print('test2')");
        GameType gameType2 = scriptController.executeScript("js", Test.class.getResource("/test.js"));
        System.out.println(gameType2);

//        GameType gameType3 = scriptControllerexecuteScript("ruby", Test.class.getResource("/test.rb"));
//        System.out.println(gameType3);
//
//        GameType gameType4 = scriptController.executeScript("python", Test.class.getResource("/test.py"));
//        System.out.println(gameType4);
    }
}
