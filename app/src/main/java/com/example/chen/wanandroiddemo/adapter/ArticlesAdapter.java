package com.example.chen.wanandroiddemo.adapter;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.core.bean.Article;
import java.util.List;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/30 20:03
 */
public class ArticlesAdapter extends BaseQuickAdapter<Article, BaseViewHolder> {

    public ArticlesAdapter(int layoutResId, @Nullable List<Article> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Article item) {
        helper.setText(R.id.author, item.getAuthor())
                .setText(R.id.superChapterName, item.getSuperChapterName())
                .setText(R.id.chapterName, item.getChapterName())
                .setText(R.id.title, item.getTitle())
                .setText(R.id.time, item.getNiceDate());
    }
}
