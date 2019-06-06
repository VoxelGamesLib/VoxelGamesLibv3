package com.voxelgameslib.game;

import java.util.Set;
import java.util.UUID;

import com.voxelgameslib.user.User;

public interface GameInstance {

    UUID getId();

    GameType getGameType();

    Set<User> getPlayers();

    Phase getActivePhase();

}
