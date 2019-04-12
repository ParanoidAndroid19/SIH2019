package com.example.mrunal.multil.womenapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    ImageView mImg;
    TextView mAppName;
    Animation mAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
        startSplashScreen();

    }

    private void startSplashScreen() {
        startAnimation();
        int SPLASH_TIME_OUT = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this,LoginPage.class));
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    private void startAnimation() {
        mAnimation = AnimationUtils.loadAnimation(SplashScreen.this,R.anim.zoom_in);
        mImg.startAnimation(mAnimation);
        //mAppName.startAnimation(mAnimation);
    }


    private void init() {
        mImg = findViewById(R.id.splash_icon_img);
        mAppName = findViewById(R.id.app_name);
    }
}