package com.voxelgameslib.script.impl;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.io.File;
import java.io.IOException;

import com.voxelgameslib.script.ScriptController;
import com.voxelgameslib.util.Identifier;

public class ScriptControllerImpl implements ScriptController {

    @Override
    public Identifier identifier() {
        return Identifier.ofVGL("DefaultScriptController");
    }

    @Override
    public Object executeScript(String lang, String script) {
        try (Context context = Context.create()) {
            Value value = context.eval(Source.newBuilder(lang, script, "src.js").build());
            return value;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object executeScript(String lang, File file) {
        try (Context context = Context.create()) {
            Value value = context.eval(Source.newBuilder(lang, file).build());
            return value;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
