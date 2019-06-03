package com.voxelgameslib.util;

import com.google.inject.Inject;

public interface Identifier {

    String getNamespace();

    String getKey();

    // static factory methods

    @Inject
    UtilModule module = UtilModule.DUMMY;

    static Identifier of(String namespace, String key) {
        return module.identifier(namespace, key);
    }

    static Identifier ofVGL(String key) {
        return module.identifier("voxelgameslib", key);
    }
}
