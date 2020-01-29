package com.example.chen.wanandroiddemo.adapter;

import android.text.Html;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.core.bean.Article;

import java.util.List;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/30 20:03
 */
public class ArticlesAdapter extends BaseQuickAdapter<Article, BaseViewHolder> {

    public ArticlesAdapter(int layoutResId, @Nullable List<Article> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Article item) {
        helper.setText(R.id.tv_author, item.getAuthor())
                .setText(R.id.tv_superChapterName, item.getSuperChapterName())
                .setText(R.id.tv_chapterName, item.getChapterName())
                .setText(R.id.tv_title, Html.fromHtml(item.getTitle()))
                .setText(R.id.tv_time, item.getNiceDate())
                .setImageResource(R.id.iv_collect, item.isCollect() ? R.drawable.ic_like : R.drawable.ic_dislike);
        helper.addOnClickListener(R.id.iv_collect);
        helper.addOnClickListener(R.id.ll_chapter);
    }
}
