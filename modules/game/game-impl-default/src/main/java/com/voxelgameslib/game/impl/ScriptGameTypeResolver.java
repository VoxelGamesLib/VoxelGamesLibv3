package com.voxelgameslib.game.impl;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.io.File;

import com.voxelgameslib.game.GameType;
import com.voxelgameslib.game.GameTypeResolver;
import com.voxelgameslib.script.ScriptController;
import com.voxelgameslib.util.Identifier;

//TODO move to own module?
public class ScriptGameTypeResolver implements GameTypeResolver {

    private File gameTypeScriptFolder;
    private ScriptController scriptController;

    @Inject
    ScriptGameTypeResolver(@Named("gameTypeScriptFolder") File gameTypeScriptFolder, ScriptController scriptController) {
        this.gameTypeScriptFolder = gameTypeScriptFolder;
        this.scriptController = scriptController;
        if(!gameTypeScriptFolder.exists()) {
            gameTypeScriptFolder.mkdirs();
        }
    }

    @Override
    public GameType resolve(Identifier identifier) {
        File file = new File(gameTypeScriptFolder, identifier.getKey() + ".js");
        if(!file.exists()) {
            file = new File(gameTypeScriptFolder, identifier.getNamespace() + "." + identifier.getKey() + ".js");
            if(!file.exists()) {
                return null;
            }
        }

        try {
            GameType gameType = scriptController.executeScript("js", file);
            if(gameType != null) {
                if(!gameType.getIdentifier().equals(identifier)) {
                    throw new IllegalStateException("Identifier doesn't match, expected " + identifier + " but loaded "
                            + gameType.getIdentifier() + " from file " + file);
                }
                return gameType;
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public Identifier identifier() {
        return Identifier.ofVGL("ScriptGameTypeResolver");
    }
}
