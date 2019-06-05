package com.voxelgameslib.text.impl;

import com.voxelgameslib.text.Text;
import com.voxelgameslib.util.Identifier;

public class TextImpl implements Text {
    @Override
    public Identifier identifier() {
        return Identifier.ofVGL("DefaultTextImpl");
    }
}
