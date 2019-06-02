import com.voxelgameslib.game.Feature;
import com.voxelgameslib.game.Game;

module com.voxelgameslib.voxelgameslib {
    requires com.voxelgameslib.game.impl;

    uses Game;
    uses Feature;
}
