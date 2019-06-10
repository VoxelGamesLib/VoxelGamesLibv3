package com.voxelgameslib.game.scriptresolver;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.io.File;

import com.voxelgameslib.game.Feature;
import com.voxelgameslib.game.FeatureResolver;
import com.voxelgameslib.game.GameType;
import com.voxelgameslib.game.GameTypeResolver;
import com.voxelgameslib.script.ScriptController;
import com.voxelgameslib.util.Identifier;

public class ScriptFeatureResolver extends AbstractScriptResolver implements FeatureResolver {

    @Inject
    ScriptFeatureResolver(@Named("featureScriptFolder") File gameTypeScriptFolder, ScriptController scriptController) {
        super(gameTypeScriptFolder, scriptController);
    }

    @Override
    public Feature resolve(Identifier identifier) {
        try {
            Feature feature = resolveType(identifier);
            if (feature != null) {
                if (!feature.getIdentifier().equals(identifier)) {
                    throw new IllegalStateException("Identifier doesn't match, expected " + identifier + " but loaded "
                            + feature.getIdentifier() + " from file " + currFile);
                }
                return feature;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public Identifier identifier() {
        return Identifier.ofVGL("ScriptFeatureResolver");
    }
}
