package com.example.chen.wanandroiddemo.presenter.activity;

import android.text.TextUtils;
import android.util.Log;

import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.contract.LoginContract;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.BaseResponse;
import com.example.chen.wanandroiddemo.core.bean.LoginData;
import com.example.chen.wanandroiddemo.utils.RxUtils;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/21 11:49
 */
public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {
    @Inject
    public LoginPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getLoginData(String useranme, String password) {
        if (TextUtils.isEmpty(useranme) || TextUtils.isEmpty(password)) {
            mView.showErrorMesssage("用户名或密码为空");
            return;
        }

        mDataManager.getLoginData(useranme, password)
                .compose(RxUtils.switchSchedulers())
                .subscribe(new Observer<BaseResponse<LoginData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(BaseResponse<LoginData> loginDataBaseResponse) {
                        LoginData data = loginDataBaseResponse.getData();
                        if (loginDataBaseResponse.getErrorCode() == Constants.SUCCESS_CODE
                                && data != null) {
                            mDataManager.setLoginStatus(true);
                            mDataManager.setLoginAccount(data.getUsername());
                            mDataManager.setLoginPassword(data.getPassword());
                            mView.showSuccessfulMesssage();
                        } else
                            mView.showErrorMesssage(loginDataBaseResponse.getErrorMsg());
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
