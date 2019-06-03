module com.voxelgameslib.game.api {
    requires org.checkerframework.checker.qual;
    requires transitive com.voxelgameslib.text.api;
    requires transitive com.voxelgameslib.user.api;
    requires transitive com.voxelgameslib.util.api;

    exports com.voxelgameslib.game;
}
