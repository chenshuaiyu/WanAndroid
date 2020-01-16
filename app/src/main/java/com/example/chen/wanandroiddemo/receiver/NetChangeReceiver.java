package com.example.chen.wanandroiddemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.chen.wanandroiddemo.bus.RxBus;
import com.example.chen.wanandroiddemo.bus.event.NetChangeEvent;

/**
 * @author chenshuaiyu
 */
public class NetChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        RxBus.getInstance().post(new NetChangeEvent());
    }
}
