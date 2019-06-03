package com.voxelgameslib.util.impl;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import com.voxelgameslib.util.Identifier;

public class IdentifierImpl implements Identifier {

    private final String namespace;
    private final String key;

    @Inject
    IdentifierImpl(@Assisted String namespace, @Assisted String key) {
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
}
