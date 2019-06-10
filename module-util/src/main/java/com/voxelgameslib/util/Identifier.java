package com.voxelgameslib.util;

import com.google.common.base.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Identifier)) return false;
        Identifier that = (Identifier) o;
        return Objects.equal(namespace, that.namespace) &&
                Objects.equal(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(namespace, key);
    }

    // static factory methods

    public static Identifier of(String namespace, String key) {
        return new Identifier(namespace, key);
    }

    public static Identifier ofVGL(String key) {
        return new Identifier("voxelgameslib", key);
    }
}
