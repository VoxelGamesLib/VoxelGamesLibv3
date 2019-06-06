package com.voxelgameslib.script;

import org.intellij.lang.annotations.Language;

import java.io.File;

import com.voxelgameslib.util.API;

public interface ScriptController extends API {

    Object executeScript(String lang, @Language("js") String script);

    Object executeScript(String lang, File file);
}
