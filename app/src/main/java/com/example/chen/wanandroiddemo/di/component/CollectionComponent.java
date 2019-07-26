package com.example.chen.wanandroiddemo.di.component;

import com.example.chen.wanandroiddemo.di.module.CollectionModule;
import com.example.chen.wanandroiddemo.ui.activity.CollectionActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author : chenshuaiyu
 * @date : 2019/7/26 22:33
 */
@Singleton
@Component(modules = CollectionModule.class)
public interface CollectionComponent {
    void inject(CollectionActivity collectionActivity);

}
