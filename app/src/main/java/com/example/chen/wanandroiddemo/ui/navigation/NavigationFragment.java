package com.example.chen.wanandroiddemo.ui.navigation;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.NavigationAdapter;
import com.example.chen.wanandroiddemo.base.fragment.BaseFragment;
import com.example.chen.wanandroiddemo.contract.NavigationContract;
import com.example.chen.wanandroiddemo.core.bean.Navigation;
import com.example.chen.wanandroiddemo.di.component.DaggerNavigationComponent;
import com.example.chen.wanandroiddemo.di.module.NavigationModule;
import com.example.chen.wanandroiddemo.presenter.NavigationPresenter;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/22 15:19
 */
public class NavigationFragment extends BaseFragment<NavigationPresenter> implements NavigationContract.View {
    @BindView(R.id.vertical_tab_layout)
    VerticalTabLayout mVerticalTabLayout;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private List<Navigation> mNavigations;
    private NavigationAdapter mNavigationAdapter;
    private TabAdapter mTabAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_nagivation;
    }

    @Override
    protected void inject() {
        DaggerNavigationComponent.builder().navigationModule(new NavigationModule()).build().inject(this);
    }

    @Override
    protected void initData() {
        mNavigations = new ArrayList<>();
        mNavigationAdapter = new NavigationAdapter(getActivity(), mNavigations);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mNavigationAdapter);

        presenter.getNavigationTab();
    }

    @Override
    public void showNavigationTab(List<Navigation> navigations) {
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
                Log.d("CCC", position + " ");
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

    }
}
