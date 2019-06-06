package com.voxelgameslib.voxelgameslib.test;

import com.google.common.base.MoreObjects;

import com.voxelgameslib.game.Feature;

public class TestFeature implements Feature {

        private String test;

        public void setTest(String test) {
            this.test = test;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("test", test)
                    .toString();
        }
    }
