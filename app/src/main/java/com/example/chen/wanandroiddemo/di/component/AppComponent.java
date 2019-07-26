package com.example.chen.wanandroiddemo.di.component;

import com.example.chen.wanandroiddemo.app.WanAndroidApp;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/16 10:39
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(WanAndroidApp app);

    DataManager getDataManager();
}
