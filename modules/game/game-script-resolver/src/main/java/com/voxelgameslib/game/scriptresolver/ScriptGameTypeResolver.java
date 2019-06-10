package com.voxelgameslib.game.scriptresolver;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.io.File;

import com.voxelgameslib.game.GameType;
import com.voxelgameslib.game.GameTypeResolver;
import com.voxelgameslib.script.ScriptController;
import com.voxelgameslib.util.Identifier;

public class ScriptGameTypeResolver extends AbstractScriptResolver implements GameTypeResolver {

    @Inject
    ScriptGameTypeResolver(@Named("gameTypeScriptFolder") File gameTypeScriptFolder, ScriptController scriptController) {
        super(gameTypeScriptFolder, scriptController);
    }

    @Override
    public GameType resolve(Identifier identifier) {
        try {
            GameType gameType = resolveType(identifier);
            if (gameType != null) {
                if (!gameType.getIdentifier().equals(identifier)) {
                    throw new IllegalStateException("Identifier doesn't match, expected " + identifier + " but loaded "
                            + gameType.getIdentifier() + " from file " + currFile);
                }
                return gameType;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public Identifier identifier() {
        return Identifier.ofVGL("ScriptGameTypeResolver");
    }
}
