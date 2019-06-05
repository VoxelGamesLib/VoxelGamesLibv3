package com.voxelgameslib.user.impl;

import com.google.common.base.MoreObjects;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import java.util.UUID;

import com.voxelgameslib.text.Text;
import com.voxelgameslib.user.User;
import com.voxelgameslib.util.Identifier;

class UserImpl implements User {

    private UUID id;
    private Text displayName;
    private String userName;

    @Inject
    UserImpl(@Assisted("uuid") UUID id, @Assisted("userName") String userName) {
        this.id = id;
        this.userName = userName;
        this.displayName = Text.ofPlain(userName);
    }

    @Override
    public UUID getUUID() {
        return id;
    }

    @Override
    public Text getDisplayName() {
        return displayName;
    }

    @Override
    public void setDisplayName(Text displayName) {
        this.displayName = displayName;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("displayName", displayName)
                .add("userName", userName)
                .toString();
    }

    @Override
    public Identifier identifier() {
        return Identifier.ofVGL("DefaultUser");
    }
}
