package com.voxelgameslib.text;

import com.voxelgameslib.util.API;

public interface Text extends API {

    static Text ofPlain(String plain) {
        return TextInjectionPoint.textModuleFactory.plain(plain);
    }
}
