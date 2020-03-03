package com.example.chen.wanandroiddemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.chen.wanandroiddemo.event.NetChangeEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * @author chenshuaiyu
 */
public class NetChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        EventBus.getDefault().post(new NetChangeEvent());
    }
}
