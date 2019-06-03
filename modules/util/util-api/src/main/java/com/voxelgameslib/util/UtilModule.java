package com.voxelgameslib.util;

public interface UtilModule {

    Identifier identifier(String namespace, String key);

    UtilModule DUMMY = new UtilModule() {
        @Override
        public Identifier identifier(String namespace, String key) {
            return null;
        }
    };
}
