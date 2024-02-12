package com.ahmad.shopforeveryone.authenticator.forgetpassword;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ahmad.shopforeveryone.authenticator.DataBase.firebaseauthmanager;
import com.ahmad.shopforeveryone.databinding.FragmentForgetPasswordBinding;

public class EnterEmail extends Fragment {
FragmentForgetPasswordBinding fragmentForgetPasswordBinding;
  EmailResultListener emailListener;
firebaseauthmanager Authmanager=new firebaseauthmanager();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
fragmentForgetPasswordBinding=FragmentForgetPasswordBinding.inflate(inflater,container,false);
//listener btn tto check email to send code
   fragmentForgetPasswordBinding.BtnSendCode.setOnClickListener(v -> {
       if (emailListener != null) {
           if (TextUtils.isEmpty(fragmentForgetPasswordBinding.Email.getText())){
               Toast.makeText(requireContext(), "fill all information", Toast.LENGTH_SHORT).show();
           }else {
               String Email=fragmentForgetPasswordBinding.Email.getText().toString();
               Authmanager.SendCodeResetEmail(Email).addOnSuccessListener(unused -> emailListener.EmailResult(true))
                       .addOnFailureListener(e -> {
                           if (e.getMessage() != null && e.getMessage().contains("There is no user")) {
                               Toast.makeText(requireContext(), "your Email not found in our database", Toast.LENGTH_SHORT).show();
                               fragmentForgetPasswordBinding.Email.setError("Email not found in our database");
                           } else {
                               Toast.makeText(requireContext(), "Password reset email failed", Toast.LENGTH_SHORT).show();
                           }

                       });
           }


       }
   });
    return fragmentForgetPasswordBinding.getRoot();
    }
    public void setEmailListener(EmailResultListener emailListener){
        this.emailListener=emailListener;
    }
}