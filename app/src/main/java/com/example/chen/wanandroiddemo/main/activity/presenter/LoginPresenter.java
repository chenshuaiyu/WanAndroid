package com.example.chen.wanandroiddemo.main.activity.presenter;

import android.text.TextUtils;

import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.main.activity.contract.LoginContract;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.LoginData;
import com.example.chen.wanandroiddemo.utils.RxUtils;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/21 11:49
 */
public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    public LoginPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getLoginData(String useranme, String password) {
        if (TextUtils.isEmpty(useranme) || TextUtils.isEmpty(password)) {
            mView.showLoginResult(false, "用户名或密码为空");
            return;
        }

        addSubcriber(
                mDataManager.getLoginData(useranme, password)
                        .compose(RxUtils.switchSchedulers())
                        .subscribe(loginDataBaseResponse -> {
                            LoginData data = loginDataBaseResponse.getData();
                            if (loginDataBaseResponse.getErrorCode() == Constants.SUCCESS_CODE
                                    && data != null) {
                                mDataManager.setLoginStatus(true);
                                mDataManager.setLoginAccount(data.getUsername());
                                mDataManager.setLoginPassword(data.getPassword());
                                mView.showLoginResult(true, loginDataBaseResponse.getErrorMsg());
                            } else {
                                mView.showLoginResult(false, loginDataBaseResponse.getErrorMsg());
                            }
                        }, Throwable::printStackTrace)
        );
    }
}
