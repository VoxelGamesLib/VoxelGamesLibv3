package com.voxelgameslib.user;

import java.util.UUID;

import com.voxelgameslib.text.Text;
import com.voxelgameslib.util.API;

public interface User extends API {

    UUID getUUID();

    Text getDisplayName();

    void setDisplayName(Text displayName);

    String getUserName();
}
