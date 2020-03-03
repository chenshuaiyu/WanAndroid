package com.example.chen.wanandroiddemo.base.view;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/11 21:34
 */
public interface BaseView {

    /**
     * 显示正常视图
     */
    void showContentView();

    /**
     * 显示正在加载视图
     */
    void showLoadingView();

    /**
     * 显示正在加载视图
     */
    void showEmptyDataView();

    /**
     * 显示网络错误视图
     */
    void showNetErrorView();

    /**
     * 显示加载错误视图
     */
    void showErrorView();

    /**
     * 重加载数据
     */
    void reLoad();
}
