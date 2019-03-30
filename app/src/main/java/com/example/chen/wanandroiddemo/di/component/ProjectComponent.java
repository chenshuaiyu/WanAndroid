package com.example.chen.wanandroiddemo.di.component;

import com.example.chen.wanandroiddemo.di.module.ProjectModule;
import com.example.chen.wanandroiddemo.ui.project.ProjectFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/19 14:30
 */
@Singleton
@Component(modules = ProjectModule.class)
public interface ProjectComponent {
    void inject(ProjectFragment projectFragment);

}
