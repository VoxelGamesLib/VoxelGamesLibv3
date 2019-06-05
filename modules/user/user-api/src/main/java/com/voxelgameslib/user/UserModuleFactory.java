package com.voxelgameslib.user;

import com.google.inject.assistedinject.Assisted;

import java.util.UUID;

import com.voxelgameslib.util.ModuleFactory;

public interface UserModuleFactory extends ModuleFactory {

    User user(@Assisted("uuid") UUID uuid, @Assisted("userName") String userName);
}
