package com.example.chen.wanandroiddemo.core.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.app.WanAndroidApp;

import javax.inject.Inject;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/16 11:09
 */
public class PreferenceHelperImpl implements PreferenceHelper {
    private SharedPreferences mPreferences;

    @Inject
    public PreferenceHelperImpl() {
        mPreferences = WanAndroidApp.getInstance().getSharedPreferences(Constants.SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
    }


}
