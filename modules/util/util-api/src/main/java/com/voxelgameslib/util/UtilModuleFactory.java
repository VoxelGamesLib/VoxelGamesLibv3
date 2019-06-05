package com.voxelgameslib.util;

import com.google.inject.assistedinject.Assisted;

public interface UtilModuleFactory {

    Identifier identifier(@Assisted("namespace") String namespace, @Assisted("key") String key);
}
