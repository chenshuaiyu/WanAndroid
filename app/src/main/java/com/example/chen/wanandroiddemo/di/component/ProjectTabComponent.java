package com.example.chen.wanandroiddemo.di.component;

import com.example.chen.wanandroiddemo.di.module.ProjectTabModule;
import com.example.chen.wanandroiddemo.ui.project.ProjectTabFragment;

import dagger.Component;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/19 14:30
 */
@Component(modules = ProjectTabModule.class)
public interface ProjectTabComponent {
    void inject(ProjectTabFragment projectTabFragment);

}
