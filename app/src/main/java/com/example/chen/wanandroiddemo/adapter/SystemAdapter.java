package com.example.chen.wanandroiddemo.adapter;

import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.core.bean.System;
import java.util.List;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/22 14:39
 */
public class SystemAdapter extends BaseQuickAdapter<System, BaseViewHolder> {

    public SystemAdapter(int layoutResId, @Nullable List<System> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, System item) {
        StringBuilder s = new StringBuilder();
        for (System childrenSystem : item.getChildren()) {
            s.append(childrenSystem.getName() + "    ");
        }
        s.delete(s.length() - 4, s.length());
        helper.setText(R.id.title, item.getName())
                .setText(R.id.content, s.toString());
    }
}
