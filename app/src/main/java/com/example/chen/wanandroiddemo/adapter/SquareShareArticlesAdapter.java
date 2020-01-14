package com.example.chen.wanandroiddemo.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.core.bean.SquareShareArticles;

import java.util.List;

public class SquareShareArticlesAdapter extends BaseQuickAdapter<SquareShareArticles.Sharearticles.Sharearticle, BaseViewHolder> {

    public SquareShareArticlesAdapter(int layoutResId, @Nullable List<SquareShareArticles.Sharearticles.Sharearticle> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SquareShareArticles.Sharearticles.Sharearticle item) {
        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_time, item.getNiceShareDate())
                .setVisible(R.id.tv_fresh, item.isFresh())
                .setImageResource(R.id.iv_collect, item.isCollect() ? R.drawable.ic_like : R.drawable.ic_dislike);
        helper.addOnClickListener(R.id.iv_collect);
    }
}
