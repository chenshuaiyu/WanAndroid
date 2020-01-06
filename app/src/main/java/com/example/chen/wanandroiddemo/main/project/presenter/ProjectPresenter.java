package com.example.chen.wanandroiddemo.main.project.presenter;

import android.util.Log;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.main.project.contract.ProjectContract;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.utils.RxUtils;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/21 8:35
 */
public class ProjectPresenter extends BasePresenter<ProjectContract.View> implements ProjectContract.Presenter {

    public ProjectPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getProjectTab() {
        Log.d("CCC", "before show");
        addSubcriber(
                mDataManager.getProjectTab()
                        .compose(RxUtils.switchSchedulers())
                        .subscribe(listBaseResponse -> {
                            mView.showTab(listBaseResponse.getData());
                            Log.d("CCC", "show");
                            mView.showContentView();
                        }, Throwable::printStackTrace)
        );
    }
}
