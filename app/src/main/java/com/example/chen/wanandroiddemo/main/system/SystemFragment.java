package com.example.chen.wanandroiddemo.main.system;

import android.content.Intent;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.SystemAdapter;
import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.base.fragment.BaseFragment;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.system.contract.SystemContract;
import com.example.chen.wanandroiddemo.core.bean.System;
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

    private List<System> mSystems;
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
        mSystems = new ArrayList<>();
        mSystemAdapter = new SystemAdapter(R.layout.item_system, mSystems);

        mRefreshRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRefreshRecyclerView.setAdapter(mSystemAdapter);
        mSystemAdapter.setOnItemClickListener((adapter, view, position) -> {
            System system = mSystems.get(position);
            Intent intent = new Intent(getActivity(), SystemArticlesActivity.class);
            intent.putExtra(Constants.SYSTEM, system);
            startActivity(intent);
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
    public void showSystem(List<System> systemList) {
        mSystems.clear();
        mSystems.addAll(systemList);
        mSystemAdapter.notifyDataSetChanged();
    }
}
