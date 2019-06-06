package com.voxelgameslib.game;

import java.util.List;

import com.voxelgameslib.text.Text;
import com.voxelgameslib.util.Identifier;

public interface GameType {

    Identifier getIdentifier();

    Text getDisplayName();

    void setDisplayName(Text displayName);

    List<Phase> getPhases();
}
