package com.example.chen.wanandroiddemo.di.component;

import com.example.chen.wanandroiddemo.di.module.CommonModule;
import com.example.chen.wanandroiddemo.ui.activity.CommonActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/19 14:30
 */
@Singleton
@Component(modules = CommonModule.class)
public interface CommonComponent {
    void inject(CommonActivity commonActivity);

}
