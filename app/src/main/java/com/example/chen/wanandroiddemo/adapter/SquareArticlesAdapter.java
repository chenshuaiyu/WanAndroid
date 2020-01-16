package com.example.chen.wanandroiddemo.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.core.bean.SquareArticles;

import java.util.List;

/**
 * @author chenshuaiyu
 */
public class SquareArticlesAdapter extends BaseQuickAdapter<SquareArticles.SquareArticle, BaseViewHolder> {

    public SquareArticlesAdapter(int layoutResId, @Nullable List<SquareArticles.SquareArticle> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SquareArticles.SquareArticle item) {
        helper.setText(R.id.tv_author, item.getShareUser())
                .setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_time, item.getNiceShareDate())
                .setVisible(R.id.tv_fresh, item.isFresh())
                .setImageResource(R.id.iv_collect, item.isCollect() ? R.drawable.ic_like : R.drawable.ic_dislike);
        helper.addOnClickListener(R.id.iv_collect);
        helper.addOnClickListener(R.id.tv_author);
    }
}
