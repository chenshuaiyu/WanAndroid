package com.example.chen.wanandroiddemo.utils;

import android.content.Context;
import android.content.Intent;
import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.core.bean.System;
import com.example.chen.wanandroiddemo.main.articledetail.ArticleActivity;
import com.example.chen.wanandroiddemo.main.activity.LoginActivity;
import com.example.chen.wanandroiddemo.main.system.SystemArticlesActivity;
import java.util.Arrays;

/**
 * @author : chenshuaiyu
 * @date : 2019/4/14 11:24
 */
public class OpenActivityUtil {
    public static void openArticleDetailActivity(Context context, int id, String link, String title, boolean collect){
        Intent intent = ArticleActivity.newIntent(context, id, link, title, collect);
        context.startActivity(intent);
    }

    public static void openLoginActivity(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    public static void openSystemArticlesActivity(Context context, String superChapterName, String chapterName, int chapterId) {
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
