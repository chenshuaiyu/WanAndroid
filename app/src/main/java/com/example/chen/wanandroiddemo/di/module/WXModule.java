package com.example.chen.wanandroiddemo.di.module;

import com.example.chen.wanandroiddemo.app.WanAndroidApp;
import com.example.chen.wanandroiddemo.core.DataManager;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/19 14:32
 */
@Module
public class WXModule {

    @Provides
    @Singleton
    DataManager getDataManager() {
        return WanAndroidApp.getInstance().getAppComponent().getDataManager();
    }
}
