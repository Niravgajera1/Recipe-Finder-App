package com.example.foodrecipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

public class Splash extends AppCompatActivity {
   ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        imageView=findViewById(R.id.Splash);

        AlphaAnimation alpha = new AlphaAnimation(0,1);
         alpha.setDuration(5000);
        imageView.startAnimation(alpha);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
              startActivity(new Intent(Splash.this,MainActivity.class));
            }
        },4000);
    }
}