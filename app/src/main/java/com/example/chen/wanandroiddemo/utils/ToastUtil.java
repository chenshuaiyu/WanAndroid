package com.example.chen.wanandroiddemo.utils;

import androidx.annotation.StringRes;
import android.widget.Toast;

import com.example.chen.wanandroiddemo.app.WanAndroidApp;

public class ToastUtil {
    public static void toast(String msg) {
        Toast.makeText(WanAndroidApp.getInstance().getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void toast(@StringRes int resId) {
        Toast.makeText(WanAndroidApp.getInstance().getApplicationContext(), resId, Toast.LENGTH_SHORT).show();
    }
}
