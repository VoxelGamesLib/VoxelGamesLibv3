package com.voxelgameslib.user.impl;

import com.google.inject.Binder;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import com.voxelgameslib.user.User;
import com.voxelgameslib.user.UserModuleFactory;
import com.voxelgameslib.util.Identifier;
import com.voxelgameslib.util.ImplementsModule;
import com.voxelgameslib.util.VGLModule;

@ImplementsModule(value = UserModuleFactory.class, prio = ImplementsModule.VGL_PRIO)
public class UserImplModule implements VGLModule {

    @Override
    public void configure(Binder binder) {
        binder.install(new FactoryModuleBuilder()
                .implement(User.class, UserImpl.class)
                .build(UserModuleFactory.class));
    }

    @Override
    public Identifier identifier() {
        return Identifier.ofVGL("DefaultUserModule");
    }
}
