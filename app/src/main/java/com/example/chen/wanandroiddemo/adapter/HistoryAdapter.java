package com.example.chen.wanandroiddemo.adapter;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.core.dao.HistoryRecord;
import java.util.List;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/23 9:33
 */
public class HistoryAdapter extends BaseQuickAdapter<HistoryRecord, BaseViewHolder> {

    public HistoryAdapter(int layoutResId, @Nullable List<HistoryRecord> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HistoryRecord item) {
        helper.setText(R.id.key, item.getData())
                .addOnClickListener(R.id.clear);
    }
}
