package com.example.chen.wanandroiddemo.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.chen.wanandroiddemo.R;

import java.util.List;

/**
 * @author : chenshuaiyu
 * @date : 2020/3/2 16:19
 */
public class SearchAssociationAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public SearchAssociationAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_content, item);
    }
}
