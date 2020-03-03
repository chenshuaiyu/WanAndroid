package com.example.chen.wanandroiddemo.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.base.activity.BaseActivity;
import com.example.chen.wanandroiddemo.bus.RxBus;
import com.example.chen.wanandroiddemo.bus.event.NightModeEvent;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.activity.contract.SettingsContract;
import com.example.chen.wanandroiddemo.main.activity.presenter.SettingsPresenter;
import com.example.chen.wanandroiddemo.utils.ShareUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author chenshuaiyu
 */
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

    @OnClick({R.id.switch_night_mode, R.id.switch_no_image_mode, R.id.switch_auto_cache, R.id.rl_feedback})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.switch_night_mode:
                setNightMode();
                break;
            case R.id.switch_no_image_mode:
                mPresenter.setNoImageMode(mNoImageModeSwitch.isChecked());
                break;
            case R.id.switch_auto_cache:
                mPresenter.setAutoCache(mAutoCacheSwitch.isChecked());
                break;
            case R.id.rl_feedback:
                ShareUtil.sendEmail(this, getString(R.string.email_client));
                break;
            default:
                break;
        }
    }

    @Override
    protected SettingsPresenter getPresenter() {
        return new SettingsPresenter(DataManager.getInstance());
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_settings;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();

        mPresenter.getNightMode();
        mPresenter.getNoImageMode();
        mPresenter.getAutoCache();
    }

    private void initToolbar() {
        mToolbar.setTitle(R.string.settings);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        assert supportActionBar != null;
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeAsUpIndicator(R.drawable.ic_back);
    }

    private void setNightMode() {
        boolean checked = mNightModeSwitch.isChecked();
        mPresenter.setNightMode(checked);
        RxBus.getInstance().post(new NightModeEvent(checked));
        //使用动画过渡重启设置界面过程
        startActivity(new Intent(SettingsActivity.this, SettingsActivity.class));
        overridePendingTransition(R.anim.activity_settings_enter, R.anim.activity_settings_exit);
        finish();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            back();
            finish();
        }
        return true;
    }

    private void back() {
        Intent intent = new Intent("intent.action.night_mode_change");
        sendBroadcast(intent);
    }
}
