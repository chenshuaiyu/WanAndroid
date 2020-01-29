package com.example.chen.wanandroiddemo.main.system.presenter;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.system.contract.SystemContract;
import com.example.chen.wanandroiddemo.utils.RxUtils;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/22 14:20
 */
public class SystemPresenter extends BasePresenter<SystemContract.View> implements SystemContract.Presenter {

    public SystemPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getSystem() {
        addSubcriber(
                mDataManager.getSystem()
                        .compose(RxUtils.switchSchedulers())
                        .subscribe(listBaseResponse -> {
                            mView.showSystem(listBaseResponse.getData());
                            mView.showContentView();
                        }, Throwable::printStackTrace)
        );
    }
}
