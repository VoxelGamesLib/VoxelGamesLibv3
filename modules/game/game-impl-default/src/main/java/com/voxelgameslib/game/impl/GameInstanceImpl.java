package com.voxelgameslib.game.impl;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import org.checkerframework.checker.nullness.qual.MonotonicNonNull;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.voxelgameslib.eventbus.EventBus;
import com.voxelgameslib.game.GameInstance;
import com.voxelgameslib.game.GameType;
import com.voxelgameslib.game.Phase;
import com.voxelgameslib.user.User;
import com.voxelgameslib.util.Identifier;

public class GameInstanceImpl implements GameInstance {

    private GameType gameType;
    private Set<User> players = new HashSet<>();
    private UUID id;
    private EventBus eventBus;
    @MonotonicNonNull
    private Phase activePhase;

    @Inject
    GameInstanceImpl(@Assisted("gameType") GameType gameType, @Assisted("uuid") UUID id) {
        this.gameType = gameType;
        this.id = id;
        this.eventBus = EventBus.newEventBus(Identifier.of("game", gameType.toString() + "-" + id.toString()));
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public GameType getGameType() {
        return gameType;
    }

    @Override
    public Set<User> getPlayers() {
        return players;
    }

    @Override
    public Phase getActivePhase() {
        return activePhase;
    }

    @Override
    public void setActivePhase(Phase activePhase) {
        this.activePhase = activePhase;
    }

    @Override
    public EventBus getEventBus() {
        return eventBus;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("gameType", gameType.getIdentifier())
                .add("id", id)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameInstanceImpl)) return false;
        GameInstanceImpl that = (GameInstanceImpl) o;
        return Objects.equal(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
