package com.example.chen.wanandroiddemo.main.square;

import androidx.annotation.NonNull;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.base.fragment.BaseFragment;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.square.contract.MySquareContract;
import com.example.chen.wanandroiddemo.main.square.presenter.MySquarePresenter;
import com.example.chen.wanandroiddemo.widget.RefreshRecyclerView;
import com.example.statelayout_lib.StateLayoutManager;

import butterknife.BindView;

public class MySquareFragment extends BaseFragment<MySquarePresenter> implements MySquareContract.View{

    @BindView(R.id.refresh_recycler_view)
    RefreshRecyclerView mRefreshRecyclerView;


    @Override
    protected MySquarePresenter getPresenter() {
        return new MySquarePresenter(DataManager.getInstance());
    }

    @Override
    protected StateLayoutManager getStateLayoutManager() {
        return new StateLayoutManager.Builder()
                .setContentLayoutResId(R.layout.fragment_my_square)
                .setOnReLoadListener(() -> mRefreshRecyclerView.reLoad())
                .build();
    }

    @Override
    protected void initView() {

    }

    @NonNull
    @Override
    public String toString() {
        return "我的广场";
    }
}
