package com.example.chen.wanandroiddemo.utils;

import com.example.chen.wanandroiddemo.app.Constants;

import java.util.Random;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/23 9:29
 */
public class ColorUtil {
    public static int randomTagColor() {
        int num = new Random().nextInt(Constants.COLOR_HOT_WORD.length);
        return Constants.COLOR_HOT_WORD[num];
    }
}
