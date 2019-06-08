package com.voxelgameslib.script;

import org.intellij.lang.annotations.Language;

import java.io.File;
import java.net.URL;

import com.voxelgameslib.util.API;

public interface ScriptController extends API {

    <T> T executeScript(String lang, @Language("js") String script);

    <T> T executeScript(String lang, File file);

    <T> T executeScript(String lang, URL url);
}
