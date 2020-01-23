package com.example.chen.wanandroiddemo.utils;

import android.content.Context;
import android.content.Intent;

import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.core.bean.Tab;
import com.example.chen.wanandroiddemo.main.articledetail.ArticleActivity;
import com.example.chen.wanandroiddemo.main.activity.LoginActivity;
import com.example.chen.wanandroiddemo.main.system.SystemArticlesActivity;

import java.util.Arrays;

/**
 * @author : chenshuaiyu
 * @date : 2019/4/14 11:24
 */
public class OpenActivityUtil {
    public static void openArticleDetailActivity(Context context, int id, String link, String title, boolean collect) {
        Intent intent = ArticleActivity.newIntent(context, id, link, title, collect);
        context.startActivity(intent);
    }

    public static void openLoginActivity(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    public static void openSystemArticlesActivity(Context context, String superChapterName, String chapterName, int chapterId) {
        Tab tab = new Tab();
        tab.setName(superChapterName);
        Tab childTab = new Tab();
        childTab.setName(chapterName);
        childTab.setId(chapterId);
        tab.setChildren(Arrays.asList(childTab));

        Intent intent = new Intent(context, SystemArticlesActivity.class);
        intent.putExtra(Constants.SYSTEM, tab);
        context.startActivity(intent);
    }
}
