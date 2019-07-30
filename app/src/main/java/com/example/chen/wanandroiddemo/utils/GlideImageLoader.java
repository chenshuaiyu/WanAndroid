package com.example.chen.wanandroiddemo.utils;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.example.chen.wanandroiddemo.app.WanAndroidApp;
import com.example.chen.wanandroiddemo.core.bean.Banner;
import com.youth.banner.loader.ImageLoader;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/18 20:27
 */
public class GlideImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object o, ImageView imageView) {
        if (!WanAndroidApp.getInstance().getAppComponent().getDataManager().getNoImageMode()){
            //Glide 加载图片
            Banner banner = (Banner) o;
            Glide.with(context).load(banner.getImagePath()).into(imageView);
        }
    }
}
