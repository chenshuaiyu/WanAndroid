package com.example.chen.wanandroiddemo.main.wx.presenter;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.wx.contract.WxContract;
import com.example.chen.wanandroiddemo.utils.RxUtils;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/19 17:22
 */
public class WxPresenter extends BasePresenter<WxContract.View> implements WxContract.Presenter {

    public WxPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getWxTab() {
        addSubcriber(
                mDataManager.getWxTab()
                        .compose(RxUtils.switchSchedulers())
                        .subscribe(listBaseResponse -> {
                            mView.showTab(listBaseResponse.getData());
                            mView.showContentView();
                        }, Throwable::printStackTrace)
        );
    }
}
