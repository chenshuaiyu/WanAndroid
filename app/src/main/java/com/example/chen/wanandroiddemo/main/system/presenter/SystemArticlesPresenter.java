package com.example.chen.wanandroiddemo.main.system.presenter;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.system.contract.SystemArticlesContract;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/22 19:56
 */
public class SystemArticlesPresenter extends BasePresenter<SystemArticlesContract.View> implements SystemArticlesContract.Presenter {

    public SystemArticlesPresenter(DataManager dataManager) {
        super(dataManager);
    }

}
