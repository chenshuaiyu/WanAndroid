package com.example.chen.wanandroiddemo.main.navigation;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.NavigationAdapter;
import com.example.chen.wanandroiddemo.base.fragment.BaseFragment;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.navigation.contract.NavigationContract;
import com.example.chen.wanandroiddemo.core.bean.Navigation;
import com.example.chen.wanandroiddemo.main.navigation.presenter.NavigationPresenter;
import com.example.chen.wanandroiddemo.utils.ToastUtil;
import com.example.chen.wanandroiddemo.widget.StateLayout.StateLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/22 15:19
 */
public class NavigationFragment extends BaseFragment<NavigationPresenter> implements NavigationContract.View {

    @BindView(R.id.vertical_tab_layout)
    VerticalTabLayout mVerticalTabLayout;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private List<Navigation> mNavigations;
    private NavigationAdapter mNavigationAdapter;
    private TabAdapter mTabAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected NavigationPresenter getPresenter() {
        return new NavigationPresenter(DataManager.getInstance());
    }

    @Override
    protected StateLayoutManager getStateLayoutManager() {
        return new StateLayoutManager.Builder()
                .setContentLayoutResId(R.layout.fragment_nagivation)
                .setOnReLoadListener(() -> mPresenter.getNavigationTab())
                .build();
    }

    @Override
    protected void initView() {
        mPresenter.subscribeEvent();

        mNavigations = new ArrayList<>();
        mNavigationAdapter = new NavigationAdapter(R.layout.item_navigation, mNavigations);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mNavigationAdapter);
        leftAndRightLinkage();
    }

    private void leftAndRightLinkage() {
        mVerticalTabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                selectTag(position);
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void selectTag(int position) {
        mRecyclerView.stopScroll();
        scrollToPosition(position);
    }

    private void scrollToPosition(int currentPosition) {
        int firstPosition = mLayoutManager.findFirstVisibleItemPosition();
        int lastPosition = mLayoutManager.findLastVisibleItemPosition();
        ToastUtil.toast(firstPosition + " : " + lastPosition);
        if (currentPosition <= firstPosition) {
            mRecyclerView.smoothScrollToPosition(currentPosition);
        } else if (currentPosition <= lastPosition) {
            int top = mRecyclerView.getChildAt(currentPosition - firstPosition).getTop();
            mRecyclerView.smoothScrollBy(0, top);
        } else {
            mRecyclerView.smoothScrollToPosition(currentPosition);
//            needScroll = true;
        }
    }

    @Override
    public void showNavigationTab(List<Navigation> navigations) {
        mNavigations.clear();
        mNavigations.addAll(navigations);
        mNavigationAdapter.notifyDataSetChanged();

        mTabAdapter = new TabAdapter() {
            @Override
            public int getCount() {
                return mNavigations.size();
            }

            @Override
            public ITabView.TabBadge getBadge(int position) {
                return null;
            }

            @Override
            public ITabView.TabIcon getIcon(int position) {
                return null;
            }

            @Override
            public ITabView.TabTitle getTitle(int position) {
                return new TabView.TabTitle.Builder()
                        .setContent(mNavigations.get(position).getName())
                        .setTextColor(ContextCompat.getColor(getActivity(), R.color.sky_blue),
                                ContextCompat.getColor(getActivity(), R.color.gray))
                        .build();
            }

            @Override
            public int getBackground(int position) {
                return -1;
            }
        };
        mVerticalTabLayout.setTabAdapter(mTabAdapter);
        mVerticalTabLayout.setIndicatorColor(R.color.white);
    }
}
