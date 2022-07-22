package com.example.cardiacrecorder;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * SplashScreenActivity is created to turn this application
 * more attractive. This splash screen will display about 2
 * sec and then it will change intent automatically to main
 * activity. Here splashscreen extends appcompatActivity
 */
public class SplashScreenActivity extends AppCompatActivity {
    /**
     * on create this method will be called show splash screen
     * @param savedInstanceState a bundle type variable
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            /**
             * after 2s showing splash screen then this method will execute
             * and change its intent from splash screen to mainActivity
             */
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this,MainActivity.class));
                finish();
            }
        },2000);

    }
}