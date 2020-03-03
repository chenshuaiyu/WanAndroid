package com.example.chen.wanandroiddemo.main.navigation;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.NavigationAdapter;
import com.example.chen.wanandroiddemo.base.fragment.BaseFragment;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.Navigation;
import com.example.chen.wanandroiddemo.main.navigation.contract.NavigationContract;
import com.example.chen.wanandroiddemo.main.navigation.presenter.NavigationPresenter;
import com.example.statelayout_lib.StateLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/22 15:19
 *
 * 参考：https://github.com/rain9155/WanAndroid/blob/master/app/src/main/java/com/example/hy/wanandroid/view/navigation/NavigationActivity.java
 */
public class NavigationFragment extends BaseFragment<NavigationPresenter> implements NavigationContract.View {

    private int mCurrentSelectedIndex;
    private boolean isDownScroll;
    private boolean isTagSelected;

    @BindView(R.id.vertical_tab_layout)
    VerticalTabLayout mVerticalTabLayout;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private List<Navigation> mNavigations = new ArrayList<>();
    private NavigationAdapter mNavigationAdapter;
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
        mNavigationAdapter = new NavigationAdapter(R.layout.item_navigation, mNavigations);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mNavigationAdapter);
        leftRightLink();
    }

    private void leftRightLink() {
        mVerticalTabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                isTagSelected = true;
                mCurrentSelectedIndex = position;
                mRecyclerView.stopScroll();
                smoothScrollToPosition(position);
            }

            @Override
            public void onTabReselected(TabView tab, int position) {
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int firstIndex = mLayoutManager.findFirstVisibleItemPosition();

                    if (!isTagSelected && mVerticalTabLayout != null) {
                        if (firstIndex != mCurrentSelectedIndex) {
                            mVerticalTabLayout.setTabSelected(firstIndex);
                            mCurrentSelectedIndex = firstIndex;
                        }
                    }
                    isTagSelected = false;

                    if (isDownScroll) {
                        isDownScroll = false;
                        isTagSelected = true;
                        int indexInRecyclerView = mCurrentSelectedIndex - firstIndex;
                        if (indexInRecyclerView >= 0 && indexInRecyclerView < recyclerView.getChildCount()) {
                            recyclerView.smoothScrollBy(0, recyclerView.getChildAt(indexInRecyclerView).getTop());
                        }
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void smoothScrollToPosition(int position) {
        int fistIndex = mLayoutManager.findFirstVisibleItemPosition();
        int lastIndex = mLayoutManager.findLastVisibleItemPosition();
        if (position < fistIndex) {
            mRecyclerView.smoothScrollToPosition(position);
        } else if (position < lastIndex) {
            mRecyclerView.smoothScrollBy(0, mRecyclerView.getChildAt(mCurrentSelectedIndex - fistIndex).getTop());
        } else {
            isDownScroll = true;
            mRecyclerView.smoothScrollToPosition(position);
        }
    }

    @Override
    public void showNavigationTab(List<Navigation> navigations) {
        mNavigations.clear();
        mNavigations.addAll(navigations);
        mNavigationAdapter.notifyDataSetChanged();
        TabAdapter tabAdapter = new TabAdapter() {
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
                        .setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.sky_blue),
                                ContextCompat.getColor(getContext(), R.color.gray))
                        .build();
            }

            @Override
            public int getBackground(int position) {
                return -1;
            }
        };
        mVerticalTabLayout.setTabAdapter(tabAdapter);
        mVerticalTabLayout.setIndicatorColor(R.color.white);
    }
}
