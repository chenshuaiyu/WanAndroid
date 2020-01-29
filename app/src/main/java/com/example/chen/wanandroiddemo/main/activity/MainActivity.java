package com.example.chen.wanandroiddemo.main.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.base.activity.BaseActivity;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.activity.contract.MainContract;
import com.example.chen.wanandroiddemo.main.activity.presenter.MainPresenter;
import com.example.chen.wanandroiddemo.main.coin.CoinActivity;
import com.example.chen.wanandroiddemo.main.collection.CollectionActivity;
import com.example.chen.wanandroiddemo.main.common.CommonActivity;
import com.example.chen.wanandroiddemo.main.homepage.HomeFragment;
import com.example.chen.wanandroiddemo.main.navigation.NavigationFragment;
import com.example.chen.wanandroiddemo.main.project.ProjectFragment;
import com.example.chen.wanandroiddemo.main.search.SearchActivity;
import com.example.chen.wanandroiddemo.main.square.SquareActivity;
import com.example.chen.wanandroiddemo.main.system.SystemFragment;
import com.example.chen.wanandroiddemo.main.todo.ToDoActivity;
import com.example.chen.wanandroiddemo.main.wx.WxFragment;
import com.example.chen.wanandroiddemo.utils.BottomNaviViewUtil;
import com.example.chen.wanandroiddemo.utils.OpenActivityUtil;
import com.example.chen.wanandroiddemo.utils.ToastUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import butterknife.BindView;

/**
 * @author chenshuaiyu
 */
public class MainActivity extends BaseActivity<MainPresenter>
        implements NavigationView.OnNavigationItemSelectedListener, MainContract.View {

    private static final long INTERVAL = 2000;
    private long exitTime = 0;
    private boolean mNightMode;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view)
    NavigationView mNaviView;
    @BindView(R.id.bottom_navi_view)
    BottomNavigationView mBottomNaviView;

    private TextView mLoginTv;
    private MenuItem mLogoutMenuItem;

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (mNightMode != mPresenter.getNightMode()) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                finish();
            }
        }
    };

    private Fragment[] mFragments = new Fragment[]{
            new HomeFragment(),
            new SystemFragment(),
            new WxFragment(),
            new NavigationFragment(),
            new ProjectFragment()
    };

    private FragmentManager mFragmentManager = getSupportFragmentManager();
    private int curFragmentIndex = 0;

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter(DataManager.getInstance());
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.subscribeEvent();
        mNightMode = mPresenter.getNightMode();
        initToolbar();
        BottomNaviViewUtil.disableShiftMode(mBottomNaviView);
        mNaviView.setNavigationItemSelectedListener(this);
        //Check状态还原
        mDrawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerClosed(View drawerView) {
                mNaviView.setCheckedItem(R.id.menu_wanandroid);
            }
        });

        curFragmentIndex = 0;
        mFragmentManager.beginTransaction()
                .add(R.id.fl_fragment_container, mFragments[0])
                .add(R.id.fl_fragment_container, mFragments[1])
                .add(R.id.fl_fragment_container, mFragments[2])
                .add(R.id.fl_fragment_container, mFragments[3])
                .add(R.id.fl_fragment_container, mFragments[4])
                .hide(mFragments[0])
                .hide(mFragments[1])
                .hide(mFragments[2])
                .hide(mFragments[3])
                .hide(mFragments[4])
                .show(mFragments[0])
                .commit();

        mBottomNaviView.setOnNavigationItemSelectedListener(menuItem -> {
            FragmentTransaction transaction = mFragmentManager.beginTransaction().hide(mFragments[curFragmentIndex]);
            switch (menuItem.getItemId()) {
                case R.id.menu_home:
                    curFragmentIndex = 0;
                    break;
                case R.id.menu_system:
                    curFragmentIndex = 1;
                    break;
                case R.id.menu_wxarticle:
                    curFragmentIndex = 2;
                    break;
                case R.id.menu_navigation:
                    curFragmentIndex = 3;
                    break;
                case R.id.menu_project:
                    curFragmentIndex = 4;
                    break;
                default:
                    break;
            }
            transaction.show(mFragments[curFragmentIndex]).commit();
            return true;
        });

        mLoginTv = mNaviView.getHeaderView(0).findViewById(R.id.tv_login);
        mLogoutMenuItem = mNaviView.getMenu().findItem(R.id.menu_logout);

        mLoginTv.setOnClickListener(v -> {
            if (!mPresenter.isLogin()) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        registerReceiver(mReceiver, new IntentFilter("intent.action.night_mode_change"));
    }

    private void initToolbar() {
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.setLoginUser();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.menu_todo:
                if (mPresenter.isLogin()) {
                    startActivity(new Intent(this, ToDoActivity.class));
                } else {
                    ToastUtil.toast(R.string.not_login_and_to_login);
                }
                break;
            case R.id.menu_website:
                startActivity(new Intent(this, CommonActivity.class));
                break;
            case R.id.menu_search:
                startActivity(new Intent(this, SearchActivity.class));
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        item.setChecked(true);
        switch (item.getItemId()) {
            case R.id.menu_collection:
                if (mPresenter.isLogin()) {
                    startActivity(new Intent(this, CollectionActivity.class));
                } else {
                    ToastUtil.toast(R.string.not_login_and_to_login);
                    OpenActivityUtil.openLoginActivity(this);
                }
                break;
            case R.id.menu_square:
                startActivity(new Intent(this, SquareActivity.class));
                break;
            case R.id.menu_coin_rank:
                startActivity(new Intent(this, CoinActivity.class));
                break;
            case R.id.menu_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
            case R.id.menu_logout:
                AlertDialog alertDialog = new AlertDialog.Builder(this)
                        .setTitle(R.string.confirm_quit)
                        .setCancelable(false)
                        .setPositiveButton(R.string.confirm, (dialog, which) -> mPresenter.logout())
                        .setNegativeButton(R.string.cancel, (dialog, which) -> {
                        })
                        .create();
                alertDialog.show();
                break;
            default:
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > INTERVAL) {
                ToastUtil.toast("再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
        }
        return false;
    }

    @Override
    public void showLoginUser(String account) {
        mLoginTv.setText(account);
    }

    @Override
    public void resetLoginUser() {
        mLoginTv.setText(R.string.login);
    }

    @Override
    public void setLogoutVisibility(boolean visiable) {
        mLogoutMenuItem.setVisible(visiable);
    }

    @Override
    public void showLogoutResult(boolean success) {
        if (success) {
            ToastUtil.toast(R.string.exit_success);
        } else {
            ToastUtil.toast(R.string.exit_fail);
        }
    }
}
