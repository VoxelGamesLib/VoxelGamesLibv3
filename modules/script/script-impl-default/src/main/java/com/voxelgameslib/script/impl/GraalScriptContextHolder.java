package com.voxelgameslib.script.impl;

import org.graalvm.polyglot.Context;

import com.voxelgameslib.script.ScriptContextHolder;

public interface GraalScriptContextHolder extends ScriptContextHolder {

    void setContext(Context context);

    Context getContext();
}
