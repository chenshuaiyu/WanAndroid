package com.example.chen.wanandroiddemo.ui.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.app.WanAndroidApp;
import com.example.chen.wanandroiddemo.base.activity.BaseActivity;
import com.example.chen.wanandroiddemo.bus.RxBus;
import com.example.chen.wanandroiddemo.bus.event.NightModeEvent;
import com.example.chen.wanandroiddemo.contract.SettingsContract;
import com.example.chen.wanandroiddemo.di.component.DaggerSettingsComponent;
import com.example.chen.wanandroiddemo.di.module.SettingsModule;
import com.example.chen.wanandroiddemo.presenter.activity.SettingsPresenter;
import com.example.chen.wanandroiddemo.utils.ShareUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingsActivity extends BaseActivity<SettingsPresenter> implements SettingsContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.switch_night_mode)
    Switch mNightModeSwitch;
    @BindView(R.id.switch_no_image_mode)
    Switch mNoImageModeSwitch;
    @BindView(R.id.switch_auto_cache)
    Switch mAutoCacheSwitch;
    @BindView(R.id.rl_feedback)
    RelativeLayout mFeedbackLayout;
    @BindView(R.id.rl_clear_cache)
    RelativeLayout mClearCacheLayout;

    @OnClick({R.id.switch_night_mode, R.id.switch_no_image_mode, R.id.switch_auto_cache, R.id.rl_feedback, R.id.rl_clear_cache})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.switch_night_mode:
                setNightMode();
                break;
            case R.id.switch_no_image_mode:
                presenter.setNoImageMode(mNoImageModeSwitch.isChecked());
                break;
            case R.id.switch_auto_cache:
                presenter.setAutoCache(mAutoCacheSwitch.isChecked());
                break;
            case R.id.rl_feedback:
                ShareUtil.sendEmail(this, getString(R.string.email_client));
                break;
            case R.id.rl_clear_cache:

                break;
            default:
                break;
        }
    }

    private void setNightMode() {
        boolean checked = mNightModeSwitch.isChecked();
        presenter.setNightMode(checked);
        RxBus.getInstance().post(new NightModeEvent(checked));
        startActivity(new Intent(SettingsActivity.this, SettingsActivity.class));
        overridePendingTransition(R.anim.enter, R.anim.exit);
        finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_settings;
    }

    @Override
    protected void inject() {
        DaggerSettingsComponent.builder()
                .appComponent(((WanAndroidApp)getApplication()).getAppComponent())
                .settingsModule(new SettingsModule())
                .build()
                .inject(this);
    }

    @Override
    protected void initView() {
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeAsUpIndicator(R.drawable.ic_back);
        supportActionBar.setTitle(R.string.settings);

        presenter.getNightMode();
        presenter.getNoImageMode();
        presenter.getAutoCache();
    }

    @Override
    protected void initData() {
        presenter.subscribeEvent();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                back();
                finish();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void showNightMode(boolean isNightMode) {
        mNightModeSwitch.setChecked(isNightMode);
    }

    @Override
    public void showNoImageMode(boolean isNoImageMode) {
        mNoImageModeSwitch.setChecked(isNoImageMode);
    }

    @Override
    public void showAutoCache(boolean isAutoCache) {
        mAutoCacheSwitch.setChecked(isAutoCache);
    }

    @Override
    public void onBackPressed() {
        back();
        super.onBackPressed();
    }

    private void back() {
        Intent intent = new Intent("intent.action.night_mode_change");
        sendBroadcast(intent);
    }
}
