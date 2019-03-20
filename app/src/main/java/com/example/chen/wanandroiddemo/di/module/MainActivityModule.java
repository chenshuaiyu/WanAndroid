package com.example.chen.wanandroiddemo.di.module;

import android.support.v4.app.FragmentManager;

import com.example.chen.wanandroiddemo.app.WanAndroidApp;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.ui.fragment.HomeFragment;
import com.example.chen.wanandroiddemo.ui.MainActivity;
import com.example.chen.wanandroiddemo.ui.fragment.WXFragment;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/17 10:32
 */
@Module
public class MainActivityModule {

    @Provides
    @Singleton
    DataManager getDataManager() {
        return WanAndroidApp.getInstance().getAppComponent().getDataManager();
    }
}
