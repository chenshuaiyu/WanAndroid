package com.example.chen.wanandroiddemo.main.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.KeyEvent;
import android.view.Menu;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.app.WanAndroidApp;
import com.example.chen.wanandroiddemo.base.activity.BaseActivity;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.activity.contract.MainContract;
import com.example.chen.wanandroiddemo.main.activity.presenter.MainPresenter;
import com.example.chen.wanandroiddemo.main.collection.CollectionActivity;
import com.example.chen.wanandroiddemo.main.common.CommonActivity;
import com.example.chen.wanandroiddemo.main.homepage.HomeFragment;
import com.example.chen.wanandroiddemo.main.navigation.NavigationFragment;
import com.example.chen.wanandroiddemo.main.project.ProjectFragment;
import com.example.chen.wanandroiddemo.main.search.SearchActivity;
import com.example.chen.wanandroiddemo.main.system.SystemFragment;
import com.example.chen.wanandroiddemo.main.wx.WXFragment;
import com.example.chen.wanandroiddemo.utils.BottomNaviViewUtil;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter>
        implements NavigationView.OnNavigationItemSelectedListener, MainContract.View {

    private static final int REQUEST_SETTINGS = 1;
    private static final int REQUEST_COLLECTION = 2;

    //连续点击 back 时间间隔
    private long exitTime = 0;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view)
    NavigationView mNaviView;
    @BindView(R.id.bottom_navi_view)
    BottomNavigationView mBottomNaviView;

    private TextView mLoginTv;
    private ActionBar mActionBar;
    private MenuItem mLogoutMenuItem;

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            mNaviView.setCheckedItem(R.id.menu_wanandroid);
            startActivity(new Intent(MainActivity.this, MainActivity.class));
            finish();
        }
    };

    private HomeFragment mHomeFragment = new HomeFragment();
    private SystemFragment mSystemFragment = new SystemFragment();
    private WXFragment mWXFragment = new WXFragment();
    private NavigationFragment mNavigationFragment = new NavigationFragment();
    private ProjectFragment mProjectFragment = new ProjectFragment();

    private FragmentManager mFragmentManager = getSupportFragmentManager();
    private Fragment curFragment;

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

        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mToolbar);
        mNaviView.setNavigationItemSelectedListener(this);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        BottomNaviViewUtil.disableShiftMode(mBottomNaviView);

        curFragment = mHomeFragment;
        mFragmentManager.beginTransaction()
                .add(R.id.fl_fragment_container, mHomeFragment)
                .add(R.id.fl_fragment_container, mSystemFragment)
                .add(R.id.fl_fragment_container, mWXFragment)
                .add(R.id.fl_fragment_container, mNavigationFragment)
                .add(R.id.fl_fragment_container, mProjectFragment)
                .hide(mSystemFragment)
                .hide(mWXFragment)
                .hide(mNavigationFragment)
                .hide(mProjectFragment)
                .show(curFragment)
                .commit();

        mBottomNaviView.setOnNavigationItemSelectedListener(menuItem -> {
            FragmentTransaction transaction = mFragmentManager.beginTransaction().hide(curFragment);
            switch (menuItem.getItemId()) {
                case R.id.menu_home:
                    if (curFragment != mHomeFragment) {
                        curFragment = mHomeFragment;
                    }
                    break;
                case R.id.menu_system:
                    if (curFragment != mSystemFragment) {
                        curFragment = mSystemFragment;
                    }
                    break;
                case R.id.menu_wxarticle:
                    if (curFragment != mWXFragment) {
                        curFragment = mWXFragment;
                    }
                    break;
                case R.id.menu_navigation:
                    if (curFragment != mNavigationFragment) {
                        curFragment = mNavigationFragment;
                    }
                    break;
                case R.id.menu_project:
                    if (curFragment != mProjectFragment) {
                        curFragment = mProjectFragment;
                    }
                    break;
                default:
                    break;
            }
            transaction.show(curFragment).commit();
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
        FragmentTransaction transaction = mFragmentManager.beginTransaction().hide(curFragment);
        switch (item.getItemId()) {
            case R.id.menu_wanandroid:

                break;
            case R.id.menu_collection:
                if (mPresenter.isLogin()) {
                    startActivityForResult(new Intent(this, CollectionActivity.class), REQUEST_COLLECTION);
                } else {
                    Toast.makeText(this, "未登录，请先登录", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;
            case R.id.menu_settings:
                startActivityForResult(new Intent(this, SettingsActivity.class), REQUEST_SETTINGS);
                break;
            case R.id.menu_logout:
                mPresenter.logout();
                break;
            default:
                break;
        }
        transaction.show(curFragment).commit();
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_SETTINGS:

                    break;
                case REQUEST_COLLECTION:
                    mNaviView.setCheckedItem(R.id.menu_wanandroid);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
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
    public void showLogoutSucceed() {
        Toast.makeText(WanAndroidApp.getInstance(), R.string.exit_success, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLogoutFailed() {
        Toast.makeText(WanAndroidApp.getInstance(), R.string.exit_fail, Toast.LENGTH_SHORT).show();
    }
}
