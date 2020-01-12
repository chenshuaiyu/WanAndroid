package com.example.chen.wanandroiddemo.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.core.bean.CoinRanks;

import java.util.List;

public class CoinRanksAdapter extends BaseQuickAdapter<CoinRanks.CoinRank, BaseViewHolder> {
    public CoinRanksAdapter(int layoutResId, @Nullable List<CoinRanks.CoinRank> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CoinRanks.CoinRank item) {
        helper.setText(R.id.tv_rank, String.valueOf(item.getRank()))
                .setText(R.id.tv_user_name, String.valueOf(item.getUsername()))
                .setText(R.id.tv_coin_count, String.valueOf(item.getCoinCount()))
                .setText(R.id.tv_lv, "lv " + item.getLevel());
    }
}
