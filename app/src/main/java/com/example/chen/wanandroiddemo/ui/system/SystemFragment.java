package com.example.chen.wanandroiddemo.ui.system;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.SystemAdapter;
import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.base.fragment.BaseFragment;
import com.example.chen.wanandroiddemo.base.fragment.BaseRefreshFragment;
import com.example.chen.wanandroiddemo.contract.SystemContract;
import com.example.chen.wanandroiddemo.core.bean.System;
import com.example.chen.wanandroiddemo.di.component.DaggerSystemComponent;
import com.example.chen.wanandroiddemo.di.module.SystemModule;
import com.example.chen.wanandroiddemo.presenter.SystemPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/22 14:19
 */
public class SystemFragment extends BaseRefreshFragment<SystemPresenter> implements SystemContract.View {
    private List<System> mSystems;
    private SystemAdapter mSystemAdapter;

    @Override
    protected void inject() {
        DaggerSystemComponent.builder().systemModule(new SystemModule()).build().inject(this);
    }

    @Override
    protected void initData() {
        mSystems = new ArrayList<>();
        mSystemAdapter = new SystemAdapter(R.layout.item_system, mSystems);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mSystemAdapter);
        mSystemAdapter.setOnItemClickListener((adapter, view, position) -> {
            System system = mSystems.get(position);
            Intent intent = new Intent(getActivity(), SystemArticlesActivity.class);
            intent.putExtra(Constants.SYSTEM, system);
            startActivity(intent);
        });

        presenter.getSystem();
    }

    @Override
    public void refresh(RefreshLayout refreshLayout) {

    }

    @Override
    public void loadMore(RefreshLayout refreshLayout) {

    }

    @Override
    public void showSystem(List<System> systemList) {
        mSystems.addAll(systemList);
        mSystemAdapter.notifyDataSetChanged();
    }
}
