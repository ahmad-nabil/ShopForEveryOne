package com.ahmad.shopforeveryone;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ahmad.shopforeveryone.authenticator.AuthManager;
import com.ahmad.shopforeveryone.databinding.SplashBinding;

public class splash extends AppCompatActivity {
    SplashBinding splashBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splashBinding = SplashBinding.inflate(getLayoutInflater());
        setContentView(splashBinding.getRoot());
        //button listener to change screen
        splashBinding.getstartBtn.setOnClickListener(v ->
        {
            startActivity(new Intent(this, AuthManager.class));
            finish();
        });
    }


}