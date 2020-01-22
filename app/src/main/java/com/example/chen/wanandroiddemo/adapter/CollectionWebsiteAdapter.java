package com.example.chen.wanandroiddemo.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.core.bean.CollectionWebsite;

import java.util.List;

/**
 * @author : chenshuaiyu
 * @date : 2020/1/21 10:45
 */
public class CollectionWebsiteAdapter extends BaseQuickAdapter<CollectionWebsite, BaseViewHolder> {
    public CollectionWebsiteAdapter(int layoutResId, @Nullable List<CollectionWebsite> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CollectionWebsite item) {
        helper.setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_link, item.getLink())
                .setImageResource(R.id.iv_collect, R.drawable.ic_like);
        helper.addOnClickListener(R.id.iv_collect);
    }
}
