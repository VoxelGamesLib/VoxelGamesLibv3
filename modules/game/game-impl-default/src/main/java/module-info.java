import com.voxelgameslib.game.GameController;
import com.voxelgameslib.game.GameInstance;
import com.voxelgameslib.game.impl.GameControllerImpl;
import com.voxelgameslib.game.impl.GameInstanceImpl;

module com.voxelgameslib.game.impl {
    requires transitive com.voxelgameslib.game.api;

    requires org.checkerframework.checker.qual;
    requires com.google.guice;
    requires slf4j.api;

    provides GameInstance with GameInstanceImpl;
    provides GameController with GameControllerImpl;

    uses GameInstance;

}
