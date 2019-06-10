package com.voxelgameslib.game.builder;

import com.voxelgameslib.game.GameInjectionPoint;
import com.voxelgameslib.game.GameType;
import com.voxelgameslib.game.Phase;
import com.voxelgameslib.util.Identifier;

public interface GameTypeBuilder {

    GameType build();

    GameTypeBuilder withPhase(Identifier identifier);

    GameTypeBuilder withPhase(Phase phase);

    static GameTypeBuilder of(Identifier identifier) {
        return GameInjectionPoint.gameModuleFactory.gameTypeBuilder(identifier);
    }
}
