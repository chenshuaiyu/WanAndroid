package com.example.chen.wanandroiddemo.main.collection;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.CollectionWebsiteAdapter;
import com.example.chen.wanandroiddemo.base.fragment.BaseFragment;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.CollectionWebsite;
import com.example.chen.wanandroiddemo.main.collection.contract.CollectionWebsiteContract;
import com.example.chen.wanandroiddemo.main.collection.presenter.CollectionWebsitePresenter;
import com.example.chen.wanandroiddemo.utils.OpenActivityUtil;
import com.example.chen.wanandroiddemo.widget.RefreshRecyclerView;
import com.example.statelayout_lib.StateLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author : chenshuaiyu
 * @date : 2020/1/21 9:56
 */
public class CollectionWebsiteFragment extends BaseFragment<CollectionWebsiteContract.Presenter> implements CollectionWebsiteContract.View {

    @BindView(R.id.refresh_recycler_view)
    RefreshRecyclerView mRefreshRecyclerView;

    private List<CollectionWebsite> mCollectionWebsites = new ArrayList<>();
    private CollectionWebsiteAdapter mCollectionWebsiteAdapter;

    @Override
    protected CollectionWebsiteContract.Presenter getPresenter() {
        return new CollectionWebsitePresenter(DataManager.getInstance());
    }

    @Override
    protected StateLayoutManager getStateLayoutManager() {
        return new StateLayoutManager.Builder()
                .setContentLayoutResId(R.layout.fragment_collection_website)
                .setOnReLoadListener(() -> mRefreshRecyclerView.reLoad())
                .build();
    }

    @Override
    protected void initView() {
        mPresenter.subscribeEvent();

        mRefreshRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mCollectionWebsiteAdapter = new CollectionWebsiteAdapter(R.layout.item_collection_website, mCollectionWebsites);
        mRefreshRecyclerView.setAdapter(mCollectionWebsiteAdapter);

        mRefreshRecyclerView.setCallback(new RefreshRecyclerView.Callback() {
            @Override
            public void refresh(int firstPage) {
                mPresenter.getCollectedWebsites();
            }

            @Override
            public void loadMore(int page) {
                mPresenter.getCollectedWebsites();
            }
        });
        mCollectionWebsiteAdapter.setOnItemClickListener((adapter, view, position) -> {
            CollectionWebsite website = mCollectionWebsites.get(position);
            OpenActivityUtil.openArticleDetailActivity(getContext(), website.getId(), website.getLink(), website.getName(), true);
        });
    }

    @Override
    public void showCollectedWebsites(List<CollectionWebsite> collectionWebsites) {
        mCollectionWebsites.clear();
        mCollectionWebsites.addAll(collectionWebsites);
        mCollectionWebsiteAdapter.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public String toString() {
        return "网站";
    }
}
