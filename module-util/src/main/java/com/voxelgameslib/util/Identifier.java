package com.voxelgameslib.util;

public class Identifier {

    private String namespace;
    private String key;

    private Identifier(String namespace, String key) {
        this.namespace = namespace;
        this.key = key;
    }

    public String getNamespace() {
        return namespace;
    }

    public String getKey() {
        return key;
    }

    @Override
    public String toString() {
        return String.format("%s:%s", getNamespace(), getKey());
    }

    // static factory methods

    public static Identifier of(String namespace, String key) {
        return new Identifier(namespace, key);
    }

    public static Identifier ofVGL(String key) {
        return new Identifier("voxelgameslib", key);
    }
}
