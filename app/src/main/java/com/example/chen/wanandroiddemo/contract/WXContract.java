package com.example.chen.wanandroiddemo.contract;

import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;
import com.example.chen.wanandroiddemo.core.bean.Tab;
import java.util.List;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/19 17:23
 */
public interface WXContract {
    interface Presenter extends IPresenter<View> {
        void getWXTab();
    }

    interface View extends BaseView {
        void showTab(List<Tab> wxTabList);
    }
}
