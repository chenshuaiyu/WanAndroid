package com.example.chen.wanandroiddemo.base.view;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/11 21:34
 */
public interface BaseView {

    /**
     * 重加载数据
     */
    void reLoad();

    /**
     * 显示加载错误视图
     */
    void showErrorView();

    /**
     * 显示正在加载视图
     */
    void showLoadingView();

    /**
     * 显示正常视图
     */
    void showNormalView();

    /**
     * 设置夜间模式
     */
    void useNightMode(boolean isNightMode);

    /**
     * 显示网络变化提示
     */
    void showNetChangeTips();
}
