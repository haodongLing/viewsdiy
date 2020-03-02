package com.haodong.pracmodule.mydagger;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * created by linghaoDo on 2019-12-25
 * description:
 * <p>
 * version:
 */
@Module
public class AppModule {
    DaggerApplication mApplication;

    public AppModule(DaggerApplication mApplication) {
        this.mApplication = mApplication;
    }

    @Singleton
    @Provides
    DaggerApplication providesApplication() {
        return mApplication;
    }

}
