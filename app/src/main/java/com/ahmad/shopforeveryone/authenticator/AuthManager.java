package com.ahmad.shopforeveryone.authenticator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.ahmad.shopforeveryone.Adapters.AdapterTapScreen;
import com.ahmad.shopforeveryone.authenticator.DataBase.firebaseauthmanager;
import com.ahmad.shopforeveryone.authenticator.login.login;
import com.ahmad.shopforeveryone.authenticator.login.signup;
import com.ahmad.shopforeveryone.Home;
import com.ahmad.shopforeveryone.databinding.ActivityAuthManagerBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class AuthManager extends AppCompatActivity {
    ActivityAuthManagerBinding authManagerBinding;
    AdapterTapScreen tapScreen;
    firebaseauthmanager AuthManager = new firebaseauthmanager();

    @Override
    protected void onStart() {
        if (AuthManager.getCurrentUser() != null) {
            startActivity(new Intent(this, Home.class));
            finish();
        }
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        authManagerBinding = ActivityAuthManagerBinding.inflate(getLayoutInflater());
        setContentView(authManagerBinding.getRoot());
        tapScreen = new AdapterTapScreen(getSupportFragmentManager(), getLifecycle());
        tapScreen.addFragment(new login(), "Login");
        tapScreen.addFragment(new signup(), "Signup");
        authManagerBinding.vPager.setAdapter(tapScreen);
        new TabLayoutMediator(authManagerBinding.tablayout, authManagerBinding.vPager, (tab, position) -> tab.setText(tapScreen.getTitle(position))).attach();
    }
}