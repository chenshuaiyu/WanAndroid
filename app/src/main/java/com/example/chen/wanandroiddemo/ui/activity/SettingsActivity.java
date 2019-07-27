package com.example.chen.wanandroiddemo.ui.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.base.activity.BaseActivity;
import com.example.chen.wanandroiddemo.bus.RxBus;
import com.example.chen.wanandroiddemo.bus.event.NightModeEvent;
import com.example.chen.wanandroiddemo.contract.SettingsContract;
import com.example.chen.wanandroiddemo.di.component.DaggerSettingsComponent;
import com.example.chen.wanandroiddemo.di.module.SettingsModule;
import com.example.chen.wanandroiddemo.presenter.activity.SettingsPresenter;

import butterknife.BindView;

public class SettingsActivity extends BaseActivity<SettingsPresenter> implements SettingsContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.switch_night_mode)
    Switch mNightModeSwitch;

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

        mNightModeSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = mNightModeSwitch.isChecked();
                presenter.setNightMode(checked);
                RxBus.getInstance().post(new NightModeEvent(checked));
                startActivity(new Intent(SettingsActivity.this, SettingsActivity.class));
                overridePendingTransition(R.anim.enter, R.anim.exit);
                finish();
            }
        });
        presenter.getNightMode();
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
    public void onBackPressed() {
        back();
        super.onBackPressed();
    }

    private void back(){
        Intent intent = new Intent("intent.action.night_mode_change");
        sendBroadcast(intent);
    }
}
