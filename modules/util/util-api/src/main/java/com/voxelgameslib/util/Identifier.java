package com.voxelgameslib.util;

public interface Identifier extends API {

    String getNamespace();

    String getKey();

    // static factory methods

    static Identifier of(String namespace, String key) {
        return UtilInjectionPoint.utilModuleFactory.identifier(namespace, key);
    }

    static Identifier ofVGL(String key) {
        return UtilInjectionPoint.utilModuleFactory.identifier("voxelgameslib", key);
    }
}
