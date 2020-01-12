package com.example.chen.wanandroiddemo.main.activity.presenter;

import android.util.Log;

import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.main.activity.contract.MainContract;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.utils.RxUtils;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/16 17:10
 */
public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    public MainPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void setLoginUser() {
        if (mDataManager.getLoginStatus()) {
            mView.showLoginUser(mDataManager.getLoginAccount());
            mView.setLogoutVisibility(true);
        } else {
            mView.resetLoginUser();
            mView.setLogoutVisibility(false);
        }
    }

    @Override
    public boolean isLogin() {
        return mDataManager.getLoginStatus();
    }

    @Override
    public void logout() {
        addSubcriber(
                mDataManager.logout()
                        .compose(RxUtils.switchSchedulers())
                        .subscribe(loginDataBaseResponse -> {
                            if (loginDataBaseResponse.getErrorCode() == Constants.SUCCESS_CODE) {
                                mDataManager.setLoginStatus(false);
                                mDataManager.setLoginAccount("");
                                mDataManager.setLoginPassword("");
                                mView.resetLoginUser();
                                mView.setLogoutVisibility(false);
                                mView.showLogoutSucceed();
                            } else {
                                mView.showLogoutFailed();
                            }
                        }, Throwable::printStackTrace)
        );
    }
}
