package com.example.chen.wanandroiddemo.di.component;

import com.example.chen.wanandroiddemo.di.module.LoginActivityModule;
import com.example.chen.wanandroiddemo.ui.activity.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/21 11:51
 */
@Singleton
@Component(modules = LoginActivityModule.class)
public interface LoginComponent {
    void inject(LoginActivity loginActivity);
}
