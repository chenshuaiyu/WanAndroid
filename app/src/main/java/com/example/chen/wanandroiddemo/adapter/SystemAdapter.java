package com.example.chen.wanandroiddemo.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.core.bean.Tab;

import java.util.List;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/22 14:39
 */
public class SystemAdapter extends BaseQuickAdapter<Tab, BaseViewHolder> {
    public SystemAdapter(int layoutResId, @Nullable List<Tab> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Tab item) {
        StringBuilder s = new StringBuilder();
        for (Tab childrenTab : item.getChildren()) {
            s.append(childrenTab.getName()).append("    ");
        }
        s.delete(s.length() - 4, s.length());
        helper.setText(R.id.tv_title, item.getName())
                .setText(R.id.tv_content, s.toString());
    }
}
