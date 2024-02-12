package com.ahmad.shopforeveryone.authenticator.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ahmad.shopforeveryone.authenticator.DataBase.firebaseauthmanager;
import com.ahmad.shopforeveryone.authenticator.forgetpassword.ForgetPasswordManager;
import com.ahmad.shopforeveryone.Home;
import com.ahmad.shopforeveryone.databinding.FragmentLoginBinding;

public class login extends Fragment implements View.OnClickListener {

FragmentLoginBinding loginBinding;
firebaseauthmanager AuthManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        loginBinding=FragmentLoginBinding.inflate(getLayoutInflater());
        //initialize Class Helper for firebase auth
        AuthManager=new firebaseauthmanager();
      //listener buttons
        loginBinding.forgetpasswordBtn.setOnClickListener(this);
        loginBinding.loginBtn.setOnClickListener(v ->{
            SignInTask();
        });
        return loginBinding.getRoot();
    }

    private void SignInTask() {
        //check all fields if empty or not
    if (TextUtils.isEmpty(loginBinding.EmailLogin.getText())||TextUtils.isEmpty(loginBinding.passwordLogin.getText())){
        if (TextUtils.isEmpty(loginBinding.EmailLogin.getText())){
            loginBinding.EmailLogin.setError("write your Email");

        }
        if (TextUtils.isEmpty(loginBinding.passwordLogin.getText())){
            loginBinding.passwordLogin.setError("write your password ");

        }

    }else {
        String email=loginBinding.EmailLogin.getText().toString();
        String password=loginBinding.passwordLogin.getText().toString();

        AuthManager.SignIn(email,password).addOnSuccessListener(authResult -> {
            startActivity(new Intent(requireContext(), Home.class));
            requireActivity().finish();
        }).addOnFailureListener(e -> Toast.makeText(requireContext(), "Failed try again or rest password", Toast.LENGTH_SHORT).show());
    }
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==loginBinding.forgetpasswordBtn.getId()) {
           startActivity(new Intent(requireContext(), ForgetPasswordManager.class));
        }}
}