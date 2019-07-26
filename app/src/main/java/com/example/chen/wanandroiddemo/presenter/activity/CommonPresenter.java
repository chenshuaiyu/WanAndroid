package com.example.chen.wanandroiddemo.presenter.activity;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.contract.CommonContract;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.BaseResponse;
import com.example.chen.wanandroiddemo.core.bean.Website;
import com.example.chen.wanandroiddemo.utils.RxUtils;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/24 17:43
 */
public class CommonPresenter extends BasePresenter<CommonContract.View> implements CommonContract.Presenter {
    @Inject
    public CommonPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getCommonWebsite() {
        mDataManager.getCommonWebsite()
                .compose(RxUtils.switchSchedulers())
                .subscribe(new Observer<BaseResponse<List<Website>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(BaseResponse<List<Website>> listBaseResponse) {
                        mView.showCommonWebsite(listBaseResponse.getData());
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
