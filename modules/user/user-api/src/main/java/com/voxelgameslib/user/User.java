package com.voxelgameslib.user;

import java.util.UUID;

import com.voxelgameslib.text.Text;

public interface User {

    UUID getUUID();

    Text getDisplayName();

    String getUserName();
}
