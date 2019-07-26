package com.example.chen.wanandroiddemo.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.chen.wanandroiddemo.R;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;

public class SplashActivity extends AppCompatActivity {

    private TextView mTimeTextView;
    private int time = 3;

    private ScheduledExecutorService mExecutorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mTimeTextView = findViewById(R.id.tv_time);

        mExecutorService = Executors.newSingleThreadScheduledExecutor();
        mExecutorService.scheduleWithFixedDelay(() -> runOnUiThread(() -> {
            mTimeTextView.setText("跳过 " + time);
            time--;
            if (time < 0) {
                jumpTOMain();
            }
        }), 1, 1, TimeUnit.SECONDS);

        mTimeTextView.setOnClickListener(v -> {
            jumpTOMain();
        });
    }

    private void jumpTOMain() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        mExecutorService.shutdown();
        finish();
    }
}
