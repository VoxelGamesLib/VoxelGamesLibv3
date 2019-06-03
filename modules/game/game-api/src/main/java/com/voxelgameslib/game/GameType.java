package com.voxelgameslib.game;

import java.util.Set;

import com.voxelgameslib.text.Text;

public interface GameType {

    String getName();

    Text getDisplayName();

    Set<Phase> getPhases();
}
