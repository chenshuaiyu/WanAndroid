package com.example.chen.wanandroiddemo.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.app.Constants;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author chenshuaiyu
 */
public class SplashActivity extends AppCompatActivity {

    private TextView mTimeTv;

    private int time = Constants.SPLASH_TIME;
    private ScheduledExecutorService mExecutorService;
    private final String skip = getResources().getString(R.string.skip) + " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mTimeTv = findViewById(R.id.tv_time);
        mTimeTv.setText(skip + time);

        mExecutorService = Executors.newSingleThreadScheduledExecutor();
        mExecutorService.scheduleWithFixedDelay(() -> runOnUiThread(() -> {
            mTimeTv.setText(skip + time);
            time--;
            if (time < 0) {
                openMainActivity();
            }
        }), 1, 1, TimeUnit.SECONDS);

        mTimeTv.setOnClickListener(v -> openMainActivity());
    }

    private void openMainActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        mExecutorService.shutdown();
        finish();
    }
}
