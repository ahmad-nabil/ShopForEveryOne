package com.ahmad.shopforeveryone.authenticator.forgetpassword;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ahmad.shopforeveryone.authenticator.AuthManager;
import com.ahmad.shopforeveryone.authenticator.DataBase.firebaseauthmanager;
import com.ahmad.shopforeveryone.databinding.FragmentRestPasswordBinding;


public class RestPassword extends Fragment {

FragmentRestPasswordBinding restPasswordBinding;
firebaseauthmanager authManager=new firebaseauthmanager();
public static RestPassword newInstance(Bundle bundle){
    RestPassword restPassword=new RestPassword();
    restPassword.setArguments(bundle);
    return restPassword;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        restPasswordBinding=FragmentRestPasswordBinding.inflate(inflater, container, false);
        Bundle bundle=getArguments();
      //gett value user id and code from arguments
        String code=bundle.getString("code");
        String UID=bundle.getString("UID");

        restPasswordBinding.BtnCheck.setOnClickListener(v -> {
            String password=restPasswordBinding.password.getText().toString();
            String repassword=restPasswordBinding.repassword.getText().toString();

        //check values and update password
            if (password.equals(repassword)){
                if (password.length()>6){
                   authManager.UpdatePassword(code,password).addOnSuccessListener(unused -> {
                        authManager.updatepasswordInFireStore(password,UID);
                        startActivity(new Intent(requireContext(),AuthManager.class));
                        requireActivity().finish();
                    }).addOnFailureListener(e -> Toast.makeText(requireContext(), "failed operation update password", Toast.LENGTH_SHORT).show());
                }else {
                    restPasswordBinding.password.setError("less than 6 chart");
                    restPasswordBinding.repassword.setError("less than 6 chart");
                }

            }else {
                restPasswordBinding.password.setError("password not match");
                restPasswordBinding.repassword.setError("password not match");
                Toast.makeText(requireContext(), "password and re password not match", Toast.LENGTH_SHORT).show();
            }
        });

        return restPasswordBinding.getRoot();
    }
}