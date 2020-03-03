package com.example.chen.wanandroiddemo.main.common;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.base.fragment.BaseFragment;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.Website;
import com.example.chen.wanandroiddemo.main.common.contract.CommonDetailContract;
import com.example.chen.wanandroiddemo.main.common.presenter.CommonDetailPresenter;
import com.example.chen.wanandroiddemo.utils.ColorUtil;
import com.example.chen.wanandroiddemo.utils.OpenActivityUtil;
import com.example.statelayout_lib.StateLayoutManager;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author chenshuaiyu
 */
public class CommonDetailFragment extends BaseFragment<CommonDetailPresenter> implements CommonDetailContract.View {

    @BindView(R.id.tag_flow_layout)
    TagFlowLayout mTagFlowLayout;

    private List<Website> mWebsites = new ArrayList<>();
    private TagAdapter<Website> mTagAdapter;

    @Override
    protected CommonDetailPresenter getPresenter() {
        return new CommonDetailPresenter(DataManager.getInstance());
    }

    @Override
    protected StateLayoutManager getStateLayoutManager() {
        return new StateLayoutManager.Builder()
                .setContentLayoutResId(R.layout.fragment_common)
                .setOnReLoadListener(() -> mPresenter.getCommonWebsite())
                .build();
    }

    @Override
    protected void initView() {
        mTagAdapter = new TagAdapter<Website>(mWebsites) {
            @Override
            public View getView(FlowLayout parent, int position, final Website website) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag, parent, false);
                TextView textView = view.findViewById(R.id.tv_hot_word);
                textView.setText(website.getName());
                view.setBackgroundColor(ColorUtil.randomTagColor());
                view.setOnClickListener(v -> OpenActivityUtil.openArticleDetailActivity(getContext(), website.getLink(), website.getName()));
                return view;
            }
        };
        mTagFlowLayout.setAdapter(mTagAdapter);
    }

    @Override
    public void showCommonWebsite(List<Website> websites) {
        mWebsites.addAll(websites);
        mTagAdapter.notifyDataChanged();
    }
}
