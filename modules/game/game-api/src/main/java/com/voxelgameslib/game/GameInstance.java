package com.voxelgameslib.game;

import java.util.Set;

import com.voxelgameslib.user.User;

public interface GameInstance {

    GameType getGameType();

    void setGameType(GameType gameType);

    Set<User> getPlayers();

    Phase getActivePhase();

}
