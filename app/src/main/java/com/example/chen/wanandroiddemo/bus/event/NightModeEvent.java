package com.example.chen.wanandroiddemo.bus.event;

/**
 * @author : chenshuaiyu
 * @date : 2019/7/17 21:20
 */
public class NightModeEvent {
    private boolean isNightMode;

    public NightModeEvent(boolean isNightMode) {
        this.isNightMode = isNightMode;
    }

    public boolean isNightMode() {
        return isNightMode;
    }

    public void setNightMode(boolean nightMode) {
        isNightMode = nightMode;
    }
}
