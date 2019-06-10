package com.voxelgameslib.game.scriptresolver;

import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.File;

import com.voxelgameslib.game.GameType;
import com.voxelgameslib.game.GameTypeResolver;
import com.voxelgameslib.script.ScriptController;
import com.voxelgameslib.util.API;
import com.voxelgameslib.util.Identifier;

public abstract class AbstractScriptResolver{

    private File folder;
    private ScriptController scriptController;

    protected File currFile = null;

    AbstractScriptResolver(File folder, ScriptController scriptController) {
        this.scriptController = scriptController;
        this.folder = folder;
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    @Nullable
    protected <T> T resolveType(Identifier identifier) {
        currFile = new File(folder, identifier.getKey() + ".js");
        if (!currFile.exists()) {
            currFile = new File(folder, identifier.getNamespace() + "." + identifier.getKey() + ".js");
            if (!currFile.exists()) {
                return null;
            }
        }

        try {
            return scriptController.executeScript("js", currFile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
