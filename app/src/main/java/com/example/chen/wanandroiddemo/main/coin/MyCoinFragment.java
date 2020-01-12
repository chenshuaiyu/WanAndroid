package com.example.chen.wanandroiddemo.main.coin;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.CoinRecordsAdapter;
import com.example.chen.wanandroiddemo.base.fragment.BaseFragment;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.Coin;
import com.example.chen.wanandroiddemo.core.bean.CoinRecords;
import com.example.chen.wanandroiddemo.main.coin.contract.MyCoinContract;
import com.example.chen.wanandroiddemo.main.coin.presenter.MyCoinPresenter;
import com.example.chen.wanandroiddemo.widget.RefreshRecyclerView;
import com.example.statelayout_lib.StateLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MyCoinFragment extends BaseFragment<MyCoinPresenter> implements MyCoinContract.View {

    @BindView(R.id.refresh_recycler_view)
    RefreshRecyclerView mRefreshRecyclerView;

    private List<CoinRecords.CoinRecord> mCoinRecordList = new ArrayList<>();
    private CoinRecordsAdapter mCoinRecordsAdapter;
    private View mInfoView;

    @Override
    protected MyCoinPresenter getPresenter() {
        return new MyCoinPresenter(DataManager.getInstance());
    }

    @Override
    protected StateLayoutManager getStateLayoutManager() {
        return new StateLayoutManager.Builder()
                .setContentLayoutResId(R.layout.fragment_my_coin)
                .setOnReLoadListener(() -> mRefreshRecyclerView.reLoad())
                .build();
    }

    @Override
    protected void initView() {
        mPresenter.subscribeEvent();

        mInfoView = LayoutInflater.from(getContext()).inflate(R.layout.my_coin, null);

        mRefreshRecyclerView.setFirstPage(1);
        mRefreshRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mCoinRecordsAdapter = new CoinRecordsAdapter(R.layout.item_coin_record, mCoinRecordList);
        mCoinRecordsAdapter.addHeaderView(mInfoView);
        mRefreshRecyclerView.setAdapter(mCoinRecordsAdapter);

        mRefreshRecyclerView.setCallback(new RefreshRecyclerView.Callback() {
            @Override
            public void refresh(int firstPage) {
                mPresenter.getCoin();
                mPresenter.getCoinRecords(firstPage);
            }

            @Override
            public void loadMore(int page) {
                mPresenter.getCoinRecords(page);
            }
        });
    }

    @Override
    public void showCoin(Coin coin) {
        TextView mCoinCountTv = mInfoView.findViewById(R.id.tv_coin_count);
        TextView mRankTv = mInfoView.findViewById(R.id.tv_rank);
        TextView mLvTv = mInfoView.findViewById(R.id.tv_lv);
        mCoinCountTv.setText(String.valueOf(coin.getCoinCount()));
        mRankTv.setText(String.valueOf(coin.getRank()));
        mLvTv.setText(String.valueOf(coin.getLevel()));
    }

    @Override
    public void showCoinRecords(CoinRecords coinRecords) {
        if (mRefreshRecyclerView.isFirstPage()) {
            mRefreshRecyclerView.addCurPage();
            mCoinRecordList.clear();
        }
        mCoinRecordList.addAll(coinRecords.getDatas());
        mCoinRecordsAdapter.notifyDataSetChanged();
    }

    @Override
    public String toString() {
        return "我的积分";
    }
}
