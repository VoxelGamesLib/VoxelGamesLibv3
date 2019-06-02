import com.voxelgameslib.game.Game;
import com.voxelgameslib.game.impl.AbstractGame;

module com.voxelgameslib.game.impl {
    requires transitive com.voxelgameslib.game.api;

    requires org.checkerframework.checker.qual;

    provides Game with AbstractGame;
}
