package com.example.chen.wanandroiddemo.di.component;

import com.example.chen.wanandroiddemo.di.MyScoped;
import com.example.chen.wanandroiddemo.di.module.SettingsModule;
import com.example.chen.wanandroiddemo.ui.activity.SettingsActivity;

import dagger.Component;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/19 14:30
 */
@MyScoped
@Component(dependencies = AppComponent.class, modules = SettingsModule.class)
public interface SettingsComponent {
    void inject(SettingsActivity settingsActivity);

}
