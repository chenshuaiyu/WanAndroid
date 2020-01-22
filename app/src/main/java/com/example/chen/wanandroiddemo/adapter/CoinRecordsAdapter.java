package com.example.chen.wanandroiddemo.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.core.bean.CoinRecord;

import java.util.List;

/**
 * @author chenshuaiyu
 */
public class CoinRecordsAdapter extends BaseQuickAdapter<CoinRecord, BaseViewHolder> {
    public CoinRecordsAdapter(int layoutResId, @Nullable List<CoinRecord> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CoinRecord item) {
        helper.setText(R.id.tv_desc, item.getDesc());
    }
}
