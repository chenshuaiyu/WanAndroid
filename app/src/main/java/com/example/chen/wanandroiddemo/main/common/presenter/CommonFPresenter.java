package com.example.chen.wanandroiddemo.main.common.presenter;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.main.common.contract.CommonFContract;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.utils.RxUtils;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/24 17:43
 */
public class CommonFPresenter extends BasePresenter<CommonFContract.View> implements CommonFContract.Presenter {

    public CommonFPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getCommonWebsite() {
        addSubcriber(
                mDataManager.getCommonWebsite()
                        .compose(RxUtils.switchSchedulers())
                        .subscribe(listBaseResponse -> {
                            mView.showCommonWebsite(listBaseResponse.getData());
                            mView.showContentView();
                        }, Throwable::printStackTrace)
        );
    }
}
