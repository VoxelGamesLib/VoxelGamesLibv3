package com.voxelgameslib.game.scriptresolver;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.io.File;

import com.voxelgameslib.game.Phase;
import com.voxelgameslib.game.PhaseResolver;
import com.voxelgameslib.script.ScriptController;
import com.voxelgameslib.util.Identifier;

public class ScriptPhaseResolver extends AbstractScriptResolver implements PhaseResolver {

    @Inject
    ScriptPhaseResolver(@Named("phaseScriptFolder") File gameTypeScriptFolder, ScriptController scriptController) {
        super(gameTypeScriptFolder, scriptController);
    }

    @Override
    public Phase resolve(Identifier identifier) {
        try {
            Phase phase = resolveType(identifier);
            if (phase != null) {
                if (!phase.getIdentifier().equals(identifier)) {
                    throw new IllegalStateException("Identifier doesn't match, expected " + identifier + " but loaded "
                            + phase.getIdentifier() + " from file " + currFile);
                }
                return phase;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public Identifier identifier() {
        return Identifier.ofVGL("ScriptPhaseResolver");
    }
}
