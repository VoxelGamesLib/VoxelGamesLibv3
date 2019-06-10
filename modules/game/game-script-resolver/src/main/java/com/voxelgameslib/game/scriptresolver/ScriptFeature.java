package com.voxelgameslib.game.scriptresolver;

import org.graalvm.polyglot.Context;

import com.voxelgameslib.game.GameInstance;
import com.voxelgameslib.game.impl.FeatureImpl;
import com.voxelgameslib.script.impl.GraalScriptContextHolder;
import com.voxelgameslib.util.Identifier;

public class ScriptFeature extends FeatureImpl implements GraalScriptContextHolder {

    private Context context;

    public ScriptFeature(Identifier identifier) {
        super(identifier);
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void closeContext() {
        this.context.close();
    }

    @Override
    public void load(GameInstance gameInstance) {
        super.load(gameInstance);
        closeContext();
    }
}
