package com.example.chen.wanandroiddemo.utils;

import android.content.Context;
import android.content.Intent;
import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.core.bean.System;
import com.example.chen.wanandroiddemo.ui.activity.ArticleDetailActivity;
import com.example.chen.wanandroiddemo.ui.activity.LoginActivity;
import com.example.chen.wanandroiddemo.ui.system.SystemArticlesActivity;
import java.util.Arrays;

/**
 * @author : chenshuaiyu
 * @date : 2019/4/14 11:24
 */
public class JumpUtils {
    public static void jumpToArticleDetailActivity(Context context, String link, String title){
        Intent intent = ArticleDetailActivity.newIntent(context, link, title);
        context.startActivity(intent);
    }

    public static void jumpToLoginActivity(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    public static void jumpToSystemArticlesActivity(Context context, String superChapterName, String chapterName, int chapterId) {
        System system = new System();
        system.setName(superChapterName);
        System childSystem = new System();
        childSystem.setName(chapterName);
        childSystem.setId(chapterId);
        system.setChildren(Arrays.asList(childSystem));

        Intent intent = new Intent(context, SystemArticlesActivity.class);
        intent.putExtra(Constants.SYSTEM, system);
        context.startActivity(intent);
    }
}
