package com.example.chen.wanandroiddemo.di.module;

import com.example.chen.wanandroiddemo.app.WanAndroidApp;
import com.example.chen.wanandroiddemo.core.DataManager;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/24 17:46
 */
@Module
public class CommonModule {

    @Provides
    @Singleton
    DataManager getDataManager() {
        return WanAndroidApp.getInstance().getAppComponent().getDataManager();
    }
}
