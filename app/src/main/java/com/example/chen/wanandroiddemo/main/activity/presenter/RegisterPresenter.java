package com.example.chen.wanandroiddemo.main.activity.presenter;

import android.text.TextUtils;

import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.LoginData;
import com.example.chen.wanandroiddemo.main.activity.contract.RegisterContract;
import com.example.chen.wanandroiddemo.utils.RxUtils;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/25 22:24
 */
public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {

    public RegisterPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getRegisterData(String useranme, String password, String repassword) {
        if (TextUtils.isEmpty(useranme) || TextUtils.isEmpty(password) || TextUtils.isEmpty(repassword)) {
            mView.showRegisterResult(false, "用户名或密码为空");
            return;
        }

        addSubcriber(
                mDataManager.getRegisterData(useranme, password, repassword)
                        .compose(RxUtils.switchSchedulers())
                        .subscribe(loginDataBaseResponse -> {
                            LoginData data = loginDataBaseResponse.getData();
                            if (loginDataBaseResponse.getErrorCode() == Constants.SUCCESS_CODE
                                    && data != null) {
                                mView.showRegisterResult(true, loginDataBaseResponse.getErrorMsg());
                            } else {
                                mView.showRegisterResult(false, loginDataBaseResponse.getErrorMsg());
                            }
                        }, Throwable::printStackTrace)
        );
    }
}
