package com.example.chen.wanandroiddemo.main.common.contract;

import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;
import com.example.chen.wanandroiddemo.core.bean.Website;

import java.util.List;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/24 17:43
 */
public interface CommonDetailContract {
    interface Presenter extends IPresenter<View> {
        void getCommonWebsite();
    }

    interface View extends BaseView {
        void showCommonWebsite(List<Website> websites);
    }
}
