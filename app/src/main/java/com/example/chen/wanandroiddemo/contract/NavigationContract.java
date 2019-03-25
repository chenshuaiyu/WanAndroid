package com.example.chen.wanandroiddemo.contract;

import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;
import com.example.chen.wanandroiddemo.core.bean.Navigation;

import java.util.List;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/22 15:20
 */
public interface NavigationContract {
    interface Presenter extends IPresenter<View> {
        void getNavigationTab();
    }

    interface View extends BaseView {
        void showNavigationTab(List<Navigation> navigations);
    }
}
