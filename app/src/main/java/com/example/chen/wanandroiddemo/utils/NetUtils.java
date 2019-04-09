package com.example.chen.wanandroiddemo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import com.example.chen.wanandroiddemo.app.WanAndroidApp;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/9 21:19
 */
public class NetUtils {
    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) WanAndroidApp.getInstance()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        return connectivityManager.getActiveNetworkInfo() != null;
    }
}
