package com.example.chen.wanandroiddemo.utils;

import android.graphics.Color;

import com.example.chen.wanandroiddemo.app.Constants;

import java.util.Random;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/23 9:29
 */
public class ColorUtil {
    public static int randomTagColor() {
        int num = new Random().nextInt(Constants.COLOR_HOTWORD.length);
        return Constants.COLOR_HOTWORD[num];
    }
}
