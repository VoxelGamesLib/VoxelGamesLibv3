package com.voxelgameslib.user;

import com.google.inject.assistedinject.Assisted;

import java.util.UUID;

public interface UserModuleFactory {

    User user(@Assisted("uuid") UUID uuid, @Assisted("userName") String userName);
}
