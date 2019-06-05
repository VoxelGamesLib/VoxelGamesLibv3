package com.voxelgameslib.util.impl;

import com.google.common.base.MoreObjects;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import com.voxelgameslib.util.Identifier;

class IdentifierImpl implements Identifier {

    private final String namespace;
    private final String key;

    @Inject
    IdentifierImpl(@Assisted("namespace") String namespace, @Assisted("key") String key) {
        this.namespace = namespace;
        this.key = key;
    }

    @Override
    public String getNamespace() {
        return namespace;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public Identifier identifier() {
        return Identifier.ofVGL("DefaultIdentifier");
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("namespace", namespace)
                .add("key", key)
                .toString();
    }
}
