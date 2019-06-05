package com.voxelgameslib.text;

import com.google.inject.assistedinject.Assisted;

public interface TextModuleFactory {

    Text plain(@Assisted("plain") String plain);
}
