package com.example.chen.wanandroiddemo.di.component;

import com.example.chen.wanandroiddemo.di.module.CommonModule;
import com.example.chen.wanandroiddemo.di.module.SettingsModule;
import com.example.chen.wanandroiddemo.ui.activity.CommonActivity;
import com.example.chen.wanandroiddemo.ui.activity.SettingsActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/19 14:30
 */
@Singleton
@Component(modules = SettingsModule.class)
public interface SettingsComponent {
    void inject(SettingsActivity settingsActivity);

}
