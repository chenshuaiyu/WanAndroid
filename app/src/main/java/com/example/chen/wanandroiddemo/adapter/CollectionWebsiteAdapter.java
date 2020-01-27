package com.example.chen.wanandroiddemo.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.core.bean.Website;

import java.util.List;

/**
 * @author : chenshuaiyu
 * @date : 2020/1/21 10:45
 */
public class CollectionWebsiteAdapter extends BaseQuickAdapter<Website, BaseViewHolder> {
    public CollectionWebsiteAdapter(int layoutResId, @Nullable List<Website> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Website item) {
        helper.setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_link, item.getLink())
                .setImageResource(R.id.iv_collect, R.drawable.ic_like);
        helper.addOnClickListener(R.id.iv_collect);
        helper.addOnClickListener(R.id.tv_edit);
    }
}
