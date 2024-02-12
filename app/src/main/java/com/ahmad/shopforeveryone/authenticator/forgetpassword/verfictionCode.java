package com.ahmad.shopforeveryone.authenticator.forgetpassword;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ahmad.shopforeveryone.authenticator.DataBase.firebaseauthmanager;
import com.ahmad.shopforeveryone.databinding.FragmentVerfictionCodeBinding;

public class verfictionCode extends Fragment {
EmailResultListener emailResultListener;
FragmentVerfictionCodeBinding binding;
firebaseauthmanager AuthManager=new firebaseauthmanager();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
binding=FragmentVerfictionCodeBinding.inflate(inflater, container, false);
binding.BtnCheck.setOnClickListener(v -> {
    String Code=binding.verfictionCode.getText().toString();
//check code
   AuthManager.VerifiedCodeResetEmail(Code).addOnCompleteListener(task -> {

                if (task.isSuccessful()) {

                    emailResultListener.CodeResult(true,Code,task.getResult());
                } else {
                    Toast.makeText(requireContext(), "failed code", Toast.LENGTH_SHORT).show();
                }
            });
});
return binding.getRoot();
    }

    public void setEmailListener(EmailResultListener emailListener){
        this.emailResultListener=emailListener;
    }
}