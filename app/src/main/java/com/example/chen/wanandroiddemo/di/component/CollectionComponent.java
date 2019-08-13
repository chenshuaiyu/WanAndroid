package com.example.chen.wanandroiddemo.di.component;

import com.example.chen.wanandroiddemo.di.MyScoped;
import com.example.chen.wanandroiddemo.di.module.CollectionModule;
import com.example.chen.wanandroiddemo.ui.activity.CollectionActivity;

import dagger.Component;

/**
 * @author : chenshuaiyu
 * @date : 2019/7/26 22:33
 */
@MyScoped
@Component(dependencies = AppComponent.class, modules = CollectionModule.class)
public interface CollectionComponent {
    void inject(CollectionActivity collectionActivity);

}
