package com.voxelgameslib.text.testimpl;

import com.google.common.base.MoreObjects;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import com.voxelgameslib.text.Text;
import com.voxelgameslib.util.Identifier;

public class TextTestImpl implements Text {

    private String plain;

    @Inject
    TextTestImpl(@Assisted("plain") String plainText) {
        this.plain = plainText;
    }

    @Override
    public Identifier identifier() {
        return Identifier.ofVGL("DefaultTextImpl");
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("plain", plain)
                .toString();
    }
}
