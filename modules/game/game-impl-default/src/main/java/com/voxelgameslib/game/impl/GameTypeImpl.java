package com.voxelgameslib.game.impl;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.voxelgameslib.game.GameType;
import com.voxelgameslib.game.Phase;
import com.voxelgameslib.text.Text;
import com.voxelgameslib.util.Identifier;

public class GameTypeImpl implements GameType {

    private final Identifier identifier;
    private Text displayName;
    private final List<Phase> phases;

    @Inject
    GameTypeImpl(@Assisted("identifier") Identifier identifier) {
        this.identifier = identifier;
        this.displayName = Text.ofPlain(identifier.getKey());
        this.phases = new ArrayList<>();
    }

    @Override
    public Identifier getIdentifier() {
        return identifier;
    }

    @Override
    public Text getDisplayName() {
        return displayName;
    }

    @Override
    public void setDisplayName(Text displayName) {
        this.displayName = displayName;
    }

    @Override
    public List<Phase> getPhases() {
        return phases;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("identifier", identifier)
                .add("phases", phases)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameTypeImpl)) return false;
        GameTypeImpl gameType = (GameTypeImpl) o;
        return Objects.equal(identifier, gameType.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(identifier);
    }
}
