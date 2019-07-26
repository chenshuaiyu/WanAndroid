package com.example.chen.wanandroiddemo.ui.activity;

import android.annotation.SuppressLint;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.base.activity.BaseActivity;
import com.example.chen.wanandroiddemo.contract.SettingsContract;
import com.example.chen.wanandroiddemo.di.component.DaggerSettingsComponent;
import com.example.chen.wanandroiddemo.di.module.SettingsModule;
import com.example.chen.wanandroiddemo.presenter.activity.SettingsPresenter;

import butterknife.BindView;

public class SettingsActivity extends BaseActivity<SettingsPresenter> implements SettingsContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_settings;
    }

    @Override
    protected void inject() {
        DaggerSettingsComponent.builder().settingsModule(new SettingsModule()).build().inject(this);
    }

    @Override
    protected void initView() {
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeAsUpIndicator(R.drawable.ic_back);
        supportActionBar.setTitle(R.string.settings);
    }

    @Override
    protected void initData() {
        presenter.subscribeEvent();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                setResult(RESULT_OK);
                finish();
                break;
            default:
                break;
        }
        return true;
    }
}
