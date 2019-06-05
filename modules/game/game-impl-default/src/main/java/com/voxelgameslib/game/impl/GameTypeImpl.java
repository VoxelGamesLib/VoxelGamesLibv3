package com.voxelgameslib.game.impl;

import java.util.Set;

import com.voxelgameslib.game.GameType;
import com.voxelgameslib.game.Phase;
import com.voxelgameslib.text.Text;

public class GameTypeImpl implements GameType {

    private String name;
    private Text displayName;
    private Set<Phase> phases;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Text getDisplayName() {
        return displayName;
    }

    @Override
    public Set<Phase> getPhases() {
        return phases;
    }
}
