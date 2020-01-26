package com.example.chen.wanandroiddemo.main.system;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.SystemAdapter;
import com.example.chen.wanandroiddemo.base.fragment.BaseFragment;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.Tab;
import com.example.chen.wanandroiddemo.main.system.contract.SystemContract;
import com.example.chen.wanandroiddemo.main.system.presenter.SystemPresenter;
import com.example.chen.wanandroiddemo.widget.RefreshRecyclerView;
import com.example.statelayout_lib.StateLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/22 14:19
 */
public class SystemFragment extends BaseFragment<SystemPresenter> implements SystemContract.View {

    @BindView(R.id.refresh_recycler_view)
    protected RefreshRecyclerView mRefreshRecyclerView;

    private List<Tab> mTabs = new ArrayList<>();
    private SystemAdapter mSystemAdapter;

    @Override
    protected SystemPresenter getPresenter() {
        return new SystemPresenter(DataManager.getInstance());
    }

    @Override
    protected StateLayoutManager getStateLayoutManager() {
        return new StateLayoutManager.Builder()
                .setContentLayoutResId(R.layout.fragment_refresh_recycler_view)
                .setOnReLoadListener(() -> mRefreshRecyclerView.reLoad())
                .build();
    }

    @Override
    protected void initView() {
        mPresenter.subscribeEvent();

        mRefreshRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSystemAdapter = new SystemAdapter(R.layout.item_system, mTabs);
        mRefreshRecyclerView.setAdapter(mSystemAdapter);
        mSystemAdapter.setOnItemClickListener((adapter, view, position) -> {
            Tab tab = mTabs.get(position);
            startActivity(SystemArticlesActivity.newIntent(getContext(), tab));
        });

        mRefreshRecyclerView.setCallback(new RefreshRecyclerView.Callback() {
            @Override
            public void refresh(int firstPage) {
                mPresenter.getSystem();
            }

            @Override
            public void loadMore(int page) {
                mPresenter.getSystem();
            }
        });
    }

    @Override
    public void showSystem(List<Tab> tabList) {
        mTabs.clear();
        mTabs.addAll(tabList);
        mSystemAdapter.notifyDataSetChanged();
    }
}
