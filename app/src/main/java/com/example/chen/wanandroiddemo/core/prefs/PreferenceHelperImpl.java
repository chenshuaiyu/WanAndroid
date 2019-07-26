package com.example.chen.wanandroiddemo.core.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.app.WanAndroidApp;
import com.example.chen.wanandroiddemo.utils.NetUtils;

import javax.inject.Inject;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/16 11:09
 */
public class PreferenceHelperImpl implements PreferenceHelper {
    private SharedPreferences mPreferences;

    @Inject
    public PreferenceHelperImpl() {
        mPreferences = WanAndroidApp.getInstance().getSharedPreferences(Constants.SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
    }


    @Override
    public void setLoginStatus(boolean status) {
        mPreferences.edit().putBoolean(Constants.LOGIN_STATUS, status).apply();
    }

    @Override
    public boolean getLoginStatus() {
        return mPreferences.getBoolean(Constants.LOGIN_STATUS, false);
    }

    @Override
    public void setLoginAccount(String account) {
        mPreferences.edit().putString(Constants.LOGIN_ACCOUNT, account).apply();
    }

    @Override
    public String getLoginAccount() {
        return mPreferences.getString(Constants.LOGIN_ACCOUNT, "");
    }

    @Override
    public void setLoginPassword(String password) {
        mPreferences.edit().putString(Constants.LOGIN_PASSWORD, password).apply();
    }

    @Override
    public String getLoginPassword() {
        return mPreferences.getString(Constants.LOGIN_PASSWORD, "");
    }

    @Override
    public void setNightMode(boolean mode) {
        mPreferences.edit().putBoolean(Constants.NIGHT_MODE, mode).apply();
    }

    @Override
    public boolean getNightMode() {
        return mPreferences.getBoolean(Constants.LOGIN_PASSWORD, false);
    }

    @Override
    public void setNetState(String state) {
        mPreferences.edit().putString(Constants.NET_STATE, state).apply();
    }

    @Override
    public String getNetState() {
        return mPreferences.getString(Constants.NET_STATE, null);
    }
}
