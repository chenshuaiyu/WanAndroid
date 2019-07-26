package com.example.chen.wanandroiddemo.di.module;

import com.example.chen.wanandroiddemo.app.WanAndroidApp;
import com.example.chen.wanandroiddemo.core.DataManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author : chenshuaiyu
 * @date : 2019/7/26 22:34
 */
@Module
public class CollectionModule {

    @Provides
    @Singleton
    DataManager getDataManager() {
        return WanAndroidApp.getInstance().getAppComponent().getDataManager();
    }
}
