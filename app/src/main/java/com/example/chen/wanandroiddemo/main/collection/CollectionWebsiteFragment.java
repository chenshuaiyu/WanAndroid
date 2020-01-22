package com.example.chen.wanandroiddemo.main.collection;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.CollectionWebsiteAdapter;
import com.example.chen.wanandroiddemo.base.fragment.BaseFragment;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.CollectionWebsite;
import com.example.chen.wanandroiddemo.main.collection.contract.CollectionWebsiteContract;
import com.example.chen.wanandroiddemo.main.collection.presenter.CollectionWebsitePresenter;
import com.example.chen.wanandroiddemo.utils.OpenActivityUtil;
import com.example.chen.wanandroiddemo.utils.ToastUtil;
import com.example.chen.wanandroiddemo.widget.RefreshRecyclerView;
import com.example.statelayout_lib.StateLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        mCollectionWebsiteAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            CollectionWebsite collectionWebsite = mCollectionWebsites.get(position);
            switch (view.getId()) {
                case R.id.iv_collect:
                    //取消收藏
                    new AlertDialog.Builder(Objects.requireNonNull(getContext()))
                            .setTitle(R.string.cancel_collect)
                            .setCancelable(false)
                            .setPositiveButton(R.string.confirm, (dialog, which) -> {
                                mPresenter.deleteWebsite(collectionWebsite.getId());
                                mCollectionWebsites.remove(position);
                                mCollectionWebsiteAdapter.notifyDataSetChanged();
                            })
                            .setNegativeButton(R.string.cancel, (dialog, which) -> {
                            })
                            .show();
                    break;
                default:
                    break;
            }
        });
        mCollectionWebsiteAdapter.setOnItemLongClickListener((adapter, view, position) -> {
            //编辑
            CollectionWebsite collectionWebsite = mCollectionWebsites.get(position);
            View editWebsiteView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_edit_website, null);
            EditText nameEt = editWebsiteView.findViewById(R.id.et_name);
            EditText linkEt = editWebsiteView.findViewById(R.id.et_link);
            nameEt.setText(collectionWebsite.getName());
            linkEt.setText(collectionWebsite.getLink());
            new AlertDialog.Builder(Objects.requireNonNull(getContext()))
                    .setTitle(R.string.edit_website)
                    .setView(editWebsiteView)
                    .setCancelable(false)
                    .setPositiveButton(R.string.confirm, (dialog, which) -> {
                        mPresenter.editWebsite(collectionWebsite.getId(), nameEt.getText().toString(), linkEt.getText().toString(), position);
                    })
                    .setNegativeButton(R.string.cancel, (dialog, which) -> {
                    })
                    .show();
            return true;
        });
    }

    @Override
    public void showCollectedWebsites(List<CollectionWebsite> collectionWebsites) {
        mCollectionWebsites.clear();
        mCollectionWebsites.addAll(collectionWebsites);
        mCollectionWebsiteAdapter.notifyDataSetChanged();
    }

    @Override
    public void showEditResult(boolean success, String name, String link, int position) {
        if (success) {
            ToastUtil.toast(R.string.edit_success);
            CollectionWebsite collectionWebsite = mCollectionWebsites.get(position);
            collectionWebsite.setName(name);
            collectionWebsite.setLink(link);
            mCollectionWebsiteAdapter.notifyDataSetChanged();
        } else {
            ToastUtil.toast(R.string.edit_fail);
        }
    }

    @Override
    public void showDeleteResult(boolean success) {
        if (success) {
            ToastUtil.toast(R.string.delete_success);
        } else {
            ToastUtil.toast(R.string.delete_fail);
        }
    }

    @NonNull
    @Override
    public String toString() {
        return "网站";
    }
}
