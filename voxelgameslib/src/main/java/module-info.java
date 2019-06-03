import com.voxelgameslib.game.Feature;

module com.voxelgameslib.voxelgameslib {
    requires com.voxelgameslib.game.impl;
    requires com.voxelgameslib.util.api;
    requires com.google.guice;

    uses Feature;
    uses com.voxelgameslib.game.GameController;
}
