package com.example.chen.wanandroiddemo.utils;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.chen.wanandroiddemo.core.bean.Banner;
import com.youth.banner.loader.ImageLoader;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/18 20:27
 */
public class GlideImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object o, ImageView imageView) {
        //Glide 加载图片
        Banner banner = (Banner) o;
        Glide.with(context).load(banner.getImagePath()).into(imageView);
    }
}
