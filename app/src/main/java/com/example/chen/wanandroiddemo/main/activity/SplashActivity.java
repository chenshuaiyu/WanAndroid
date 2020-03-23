package com.example.chen.wanandroiddemo.main.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.utils.RxUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * @author chenshuaiyu
 */
public class SplashActivity extends AppCompatActivity {

    private TextView mTimeTv;

    private int time = Constants.SPLASH_TIME;
    private final String skip = "跳过 ";
    private Disposable mDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mTimeTv = findViewById(R.id.tv_time);
        mTimeTv.setText(skip + time);

        mDisposable = Observable.intervalRange(0, 3, 0, 1, TimeUnit.SECONDS)
                .compose(RxUtils.switchSchedulers())
                .subscribe(aLong -> mTimeTv.setText(skip + (3 - aLong)),
                        Throwable::printStackTrace,
                        this::openMainActivity);

        mTimeTv.setOnClickListener(v -> openMainActivity());
    }

    private void openMainActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        mDisposable.dispose();
        finish();
    }
}
