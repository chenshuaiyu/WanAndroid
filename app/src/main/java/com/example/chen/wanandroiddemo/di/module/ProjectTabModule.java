package com.example.chen.wanandroiddemo.di.module;

import com.example.chen.wanandroiddemo.app.WanAndroidApp;
import com.example.chen.wanandroiddemo.core.DataManager;
import dagger.Module;
import dagger.Provides;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/21 10:39
 */
@Module
public class ProjectTabModule {

    @Provides
    DataManager getDataManager() {
        return WanAndroidApp.getInstance().getAppComponent().getDataManager();
    }
}
