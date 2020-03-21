package com.example.jungsoosmarket.ui.splash;

import android.content.Intent;
import android.os.Bundle;

import com.example.jungsoosmarket.R;
import com.example.jungsoosmarket.ui.common.BaseActivity;
import com.example.jungsoosmarket.ui.home.HomeActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;

public class SplashActivity extends BaseActivity {

    public static final int SPLASH_DELAY = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        waitAndNavigate();

    }

    /**
     * Method for waiting for a specified amount of time
     * and then initiating the navigation to the Home screen.
     */
    private void waitAndNavigate() {
        Completable.complete()
                .delay(SPLASH_DELAY, TimeUnit.SECONDS)
                .doOnComplete(this::startHome)
                .subscribe();
    }

    /**
     * Method creates the intent for starting the Home Activity
     * & starts it.
     */
    private void startHome(){
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }
}
