package com.voxelgameslib.text;

import com.google.inject.assistedinject.Assisted;

import com.voxelgameslib.util.ModuleFactory;

public interface TextModuleFactory extends ModuleFactory {

    Text plain(@Assisted("plain") String plain);
}
