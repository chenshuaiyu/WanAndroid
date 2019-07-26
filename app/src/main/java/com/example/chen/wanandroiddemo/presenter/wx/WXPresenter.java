package com.example.chen.wanandroiddemo.presenter.wx;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.contract.WXContract;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.BaseResponse;
import com.example.chen.wanandroiddemo.core.bean.Tab;
import com.example.chen.wanandroiddemo.utils.RxUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/19 17:22
 */
public class WXPresenter extends BasePresenter<WXContract.View> implements WXContract.Presenter {

    @Inject
    public WXPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getWXTab() {
        mDataManager.getWXTab()
                .compose(RxUtils.switchSchedulers())
                .subscribe(new Observer<BaseResponse<List<Tab>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(BaseResponse<List<Tab>> listBaseResponse) {
                        mView.showTab(listBaseResponse.getData());
                        mView.showNormalView();
                    }
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }
}
