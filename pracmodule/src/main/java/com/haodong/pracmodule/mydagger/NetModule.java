package com.haodong.pracmodule.mydagger;

import dagger.Module;

/**
 * created by linghaoDo on 2019-12-25
 * description:
 * <p>
 * version:
 */
@Module
public class NetModule {
    String baseUrl;

    public NetModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }


}
