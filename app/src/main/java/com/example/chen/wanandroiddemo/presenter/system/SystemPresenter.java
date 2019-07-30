package com.example.chen.wanandroiddemo.presenter.system;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.contract.SystemContract;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.BaseResponse;
import com.example.chen.wanandroiddemo.core.bean.System;
import com.example.chen.wanandroiddemo.utils.RxUtil;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/22 14:20
 */
public class SystemPresenter extends BasePresenter<SystemContract.View> implements SystemContract.Presenter {
    @Inject
    public SystemPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getSystem() {
        mDataManager.getSystem()
                .compose(RxUtil.switchSchedulers())
                .subscribe(new Observer<BaseResponse<List<System>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(BaseResponse<List<System>> listBaseResponse) {
                        mView.showSystem(listBaseResponse.getData());
                        mView.showNormalView();
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }
}
