package com.example.chen.wanandroiddemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NightModeChangeReceiver extends BroadcastReceiver {

    private static Callback mCallback;

    public static void setCallback(Callback callback) {
        mCallback = callback;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        mCallback.call();
    }

    public interface Callback {
        void call();
    }
}
