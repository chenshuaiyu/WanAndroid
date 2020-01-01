package com.example.chen.wanandroiddemo.main.common.presenter;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.main.common.contract.CommonFContract;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.BaseResponse;
import com.example.chen.wanandroiddemo.core.bean.Website;
import com.example.chen.wanandroiddemo.utils.RxUtil;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

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
        mDataManager.getCommonWebsite()
                .compose(RxUtil.switchSchedulers())
                .subscribe(new Observer<BaseResponse<List<Website>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(BaseResponse<List<Website>> listBaseResponse) {
                        mView.showCommonWebsite(listBaseResponse.getData());
                        mView.showContentView();
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
