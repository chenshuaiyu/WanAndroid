package com.example.chen.wanandroiddemo.main.common.presenter;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.common.contract.CommonContract;

/**
 * @author chenshuaiyu
 */
public class CommonPresenter extends BasePresenter<CommonContract.View> implements CommonContract.Presenter {
    public CommonPresenter(DataManager dataManager) {
        super(dataManager);
    }
}
