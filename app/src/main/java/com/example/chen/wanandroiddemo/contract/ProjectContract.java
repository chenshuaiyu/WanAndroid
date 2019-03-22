package com.example.chen.wanandroiddemo.contract;

import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;
import com.example.chen.wanandroiddemo.core.bean.Tab;

import java.util.List;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/21 8:34
 */
public interface ProjectContract {
    interface Presenter extends IPresenter<View> {
        void getProjectTab();
    }

    interface View extends BaseView {
        void showTab(List<Tab> projectTabList);
    }
}
