package com.example.chen.wanandroiddemo.presenter.project;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.contract.ProjectContract;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.BaseResponse;
import com.example.chen.wanandroiddemo.core.bean.Tab;
import com.example.chen.wanandroiddemo.utils.RxUtils;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/21 8:35
 */
public class ProjectPresenter extends BasePresenter<ProjectContract.View> implements ProjectContract.Presenter {
    @Inject
    public ProjectPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getProjectTab() {
        mDataManager.getProjectTab()
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
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }
}
