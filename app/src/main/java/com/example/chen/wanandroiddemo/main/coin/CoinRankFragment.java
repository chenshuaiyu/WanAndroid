package com.example.chen.wanandroiddemo.main.coin;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.CoinRanksAdapter;
import com.example.chen.wanandroiddemo.base.fragment.BaseFragment;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.Coin;
import com.example.chen.wanandroiddemo.main.coin.contract.CoinRankContract;
import com.example.chen.wanandroiddemo.main.coin.presenter.CoinRankPresenter;
import com.example.chen.wanandroiddemo.widget.RefreshRecyclerView;
import com.example.statelayout_lib.StateLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author chenshuaiyu
 */
public class CoinRankFragment extends BaseFragment<CoinRankPresenter> implements CoinRankContract.View {

    @BindView(R.id.refresh_recycler_view)
    RefreshRecyclerView mRefreshRecyclerView;

    private List<Coin> mCoinRankList = new ArrayList<>();
    private CoinRanksAdapter mCoinRanksAdapter;

    @Override
    protected CoinRankPresenter getPresenter() {
        return new CoinRankPresenter(DataManager.getInstance());
    }

    @Override
    protected StateLayoutManager getStateLayoutManager() {
        return new StateLayoutManager.Builder()
                .setContentLayoutResId(R.layout.fragment_coin_rank)
                .setOnReLoadListener(() -> mRefreshRecyclerView.reLoad())
                .build();
    }

    @Override
    protected void initView() {
        mPresenter.subscribeEvent();

        mRefreshRecyclerView.setFirstPage(1);
        mRefreshRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mCoinRanksAdapter = new CoinRanksAdapter(R.layout.item_coin_rank, mCoinRankList);
        mRefreshRecyclerView.setAdapter(mCoinRanksAdapter);

        mRefreshRecyclerView.setCallback(new RefreshRecyclerView.Callback() {
            @Override
            public void refresh(int firstPage) {
                mPresenter.getCoinRanks(firstPage);
            }

            @Override
            public void loadMore(int page) {
                mPresenter.getCoinRanks(page);
            }
        });
    }

    @Override
    public void showCoinRanks(List<Coin> coinRanks) {
        if (mRefreshRecyclerView.isFirstPage()) {
            mRefreshRecyclerView.addCurPage();
            mCoinRankList.clear();
        }
        mCoinRankList.addAll(coinRanks);
        mCoinRanksAdapter.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public String toString() {
        return "积分排行榜";
    }
}
