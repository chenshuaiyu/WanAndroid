package com.example.chen.wanandroiddemo.widget;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.chen.wanandroiddemo.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class RefreshRecyclerView extends RelativeLayout {

    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mSmartRefreshLayout;

    private Callback mCallback;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private int mFirstPage = 0;
    private int mCurPage = 0;

    public void setFirstPage(int firstPage) {
        this.mFirstPage = firstPage;
    }

    //由于是异步加载，refresh时需手动 add current page
    public void addCurPage() {
        mCurPage++;
    }

    public boolean isFirstPage() {
        return mCurPage == mFirstPage;
    }

    public RefreshRecyclerView(Context context) {
        this(context, null);
    }

    public RefreshRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View.inflate(getContext(), R.layout.refresh_recycler_view, this);
        mRecyclerView = findViewById(R.id.recycler_view);
        mSmartRefreshLayout = findViewById(R.id.refresh_layout);

        mSmartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mCurPage = mFirstPage;
            mCallback.refresh(mCurPage);
            refreshLayout.finishRefresh(1000);
        });
        mSmartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mCallback.loadMore(mCurPage++);
            refreshLayout.finishLoadMore(1000);
        });
    }

    public void reLoad() {
        mCurPage = mFirstPage;
        if (mCallback != null) {
            mCallback.refresh(mCurPage);
        }
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        mAdapter = adapter;
        mRecyclerView.setAdapter(mAdapter);
    }

    public void setLayoutManager(LinearLayoutManager layoutManager) {
        mLayoutManager = layoutManager;
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    public interface Callback {
        void refresh(int firstPage);

        void loadMore(int page);
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }
}
