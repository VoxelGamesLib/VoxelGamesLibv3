package com.voxelgameslib.game.impl;

import org.checkerframework.checker.nullness.qual.MonotonicNonNull;

import java.util.HashSet;
import java.util.Set;

import com.voxelgameslib.game.GameInstance;
import com.voxelgameslib.game.GameType;
import com.voxelgameslib.game.Phase;
import com.voxelgameslib.user.User;

public class GameInstanceImpl implements GameInstance {

    @MonotonicNonNull
    private GameType gameType;
    private Set<User> players = new HashSet<>();
    @MonotonicNonNull
    private Phase activePhase;

    @Override
    public GameType getGameType() {
        return gameType;
    }

    @Override
    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    @Override
    public Set<User> getPlayers() {
        return players;
    }

    @Override
    public Phase getActivePhase() {
        return activePhase;
    }
}
