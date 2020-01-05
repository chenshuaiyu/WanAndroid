package com.example.chen.wanandroiddemo.main.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
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
import com.example.chen.wanandroiddemo.utils.ToastUtil;

import java.util.Arrays;

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

    private Fragment[] mFragments = new Fragment[]{
            new HomeFragment(),
            new SystemFragment(),
            new WXFragment(),
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

        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mToolbar);
        mNaviView.setNavigationItemSelectedListener(this);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        BottomNaviViewUtil.disableShiftMode(mBottomNaviView);

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
        switch (item.getItemId()) {
            case R.id.menu_wanandroid:
                break;
            case R.id.menu_collection:
                if (mPresenter.isLogin()) {
                    startActivityForResult(new Intent(this, CollectionActivity.class), REQUEST_COLLECTION);
                } else {
                    //bug：未选中WanAndroid栏
                    mNaviView.setCheckedItem(R.id.menu_wanandroid);
                    ToastUtil.toast("未登录，请先登录");
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;
            case R.id.menu_settings:
                startActivityForResult(new Intent(this, SettingsActivity.class), REQUEST_SETTINGS);
                break;
            case R.id.menu_logout:
                AlertDialog alertDialog = new AlertDialog.Builder(this)
                        .setTitle(R.string.confirm_quit)
                        .setCancelable(false)
                        .setPositiveButton(R.string.confirm, (dialog, which) -> {
                            mPresenter.logout();
                            mNaviView.setCheckedItem(R.id.menu_wanandroid);
                        })
                        .setNegativeButton(R.string.cancel, (dialog, which) -> mNaviView.setCheckedItem(R.id.menu_wanandroid))
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
    public void showLogoutSucceed() {
        ToastUtil.toast(R.string.exit_success);
    }

    @Override
    public void showLogoutFailed() {
        ToastUtil.toast(R.string.exit_fail);
    }
}
