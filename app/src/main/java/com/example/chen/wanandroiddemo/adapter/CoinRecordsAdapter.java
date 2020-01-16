package com.example.chen.wanandroiddemo.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.core.bean.CoinRecords;

import java.util.List;

/**
 * @author chenshuaiyu
 */
public class CoinRecordsAdapter extends BaseQuickAdapter<CoinRecords.CoinRecord, BaseViewHolder> {
    public CoinRecordsAdapter(int layoutResId, @Nullable List<CoinRecords.CoinRecord> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CoinRecords.CoinRecord item) {
        helper.setText(R.id.tv_desc, item.getDesc());
    }
}
