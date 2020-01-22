package com.example.chen.wanandroiddemo.adapter;

import android.text.Html;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.core.bean.CollectionArticle;

import java.util.List;

/**
 * @author : chenshuaiyu
 * @date : 2020/1/21 15:57
 */
public class CollectionArticlesAdapter extends BaseQuickAdapter<CollectionArticle, BaseViewHolder> {
    public CollectionArticlesAdapter(int layoutResId, @Nullable List<CollectionArticle> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CollectionArticle item) {
        helper.setText(R.id.tv_author, item.getAuthor())
                .setText(R.id.tv_chapterName, item.getChapterName())
                .setText(R.id.tv_title, Html.fromHtml(item.getTitle()))
                .setText(R.id.tv_time, item.getNiceDate())
                .setImageResource(R.id.iv_collect, R.drawable.ic_like);
        helper.addOnClickListener(R.id.iv_collect);
        helper.addOnClickListener(R.id.ll_chapter);
    }
}
