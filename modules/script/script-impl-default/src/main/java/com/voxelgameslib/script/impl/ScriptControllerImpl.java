package com.voxelgameslib.script.impl;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Engine;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import com.voxelgameslib.script.ScriptContextHolder;
import com.voxelgameslib.script.ScriptController;
import com.voxelgameslib.util.Identifier;

public class ScriptControllerImpl implements ScriptController {

    private Engine engine = Engine.create();

    @Override
    public Identifier identifier() {
        return Identifier.ofVGL("DefaultScriptController");
    }

    @Override
    public <T> T executeScript(String lang, String script) {
        try {
            return eval(Source.newBuilder(lang, script, "src.js").build());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public <T> T executeScript(String lang, File file) {
        try {
            return eval(Source.newBuilder(lang, file).build());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public <T> T executeScript(String lang, URL url) {
        try {
            return eval(Source.newBuilder(lang, url).build());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private <T> T eval(Source source) {
        Context context = null;
        T val = null;
        try {
            context = Context.newBuilder().engine(engine).allowAllAccess(true).build();
            Value value = context.eval(source);
            if(value.isHostObject()) {
                val = value.asHostObject();
                return val;
            }else if(value.isNull()) {
                return null;
            }else {
                System.out.println("Unknown value! " + value);
                return null;
            }
        }finally {
            if(val != null && val instanceof GraalScriptContextHolder) {
                GraalScriptContextHolder scriptContextHolder = (GraalScriptContextHolder) val;
                scriptContextHolder.setContext(context);
            }else {
                context.close();
            }
        }
    }
}
