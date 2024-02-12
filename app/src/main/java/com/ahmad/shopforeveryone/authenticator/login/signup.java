package com.ahmad.shopforeveryone.authenticator.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ahmad.shopforeveryone.authenticator.DataBase.DataUser;
import com.ahmad.shopforeveryone.authenticator.DataBase.firebaseauthmanager;
import com.ahmad.shopforeveryone.Home;
import com.ahmad.shopforeveryone.databinding.FragmentSignupBinding;


public class signup extends Fragment {
    FragmentSignupBinding signupBinding;
    firebaseauthmanager AuthManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        signupBinding=FragmentSignupBinding.inflate(inflater,container,false);
//initialize Class Helper for firebase auth
        AuthManager=new firebaseauthmanager();
        //listener for signup
        signupBinding.signupBtn.setOnClickListener(v->{
          CheckFields();
      });

        return signupBinding.getRoot();
    }

    private void CheckFields() {
//check fields if empty or not
     if (!TextUtils.isEmpty(signupBinding.FullnameSignup.getText())&&
!TextUtils.isEmpty(signupBinding.EmailSignup.getText())&&
!TextUtils.isEmpty(signupBinding.passwordSignup.getText())&&
!TextUtils.isEmpty(signupBinding.repasswordsignup.getText())){
//get value from Edit Text
         String Password=signupBinding.passwordSignup.getText().toString();
         String RePassword=signupBinding.repasswordsignup.getText().toString();
         String Email=signupBinding.EmailSignup.getText().toString();
         String FullName=signupBinding.FullnameSignup.getText().toString();
     //check if password and re password same values
       if (TextUtils.equals(Password,RePassword)){
           if (RePassword.length()>6){
           //add values tto object
               DataUser dataUser=new DataUser();
               dataUser.Email=Email;
               dataUser.FullName=FullName;
               dataUser.password=Password;
               AuthManager.Register(dataUser).addOnSuccessListener(authResult -> {
                   AuthManager.RegisterUserData(dataUser);
                   Toast.makeText(requireContext(), "signed up ", Toast.LENGTH_SHORT).show();
                   requireActivity().startActivity(new Intent(requireContext(), Home.class));
                   requireActivity().finish();
               }).addOnFailureListener(e -> Toast.makeText(requireContext(), "failed try another email ", Toast.LENGTH_SHORT).show());

           }
           else {
               //add error notes to all Edit text if needed
               signupBinding.passwordSignup.setError("your password less than 6 chart");
               signupBinding.repasswordsignup.setError("your password less than 6 chart");
               Toast.makeText(requireContext(), "your password less than 6 chart", Toast.LENGTH_SHORT).show();
           }
       }else {

           signupBinding.passwordSignup.setError("password and re password not same");
           signupBinding.repasswordsignup.setError("password and re password not same");
           Toast.makeText(requireContext(), "password and re password not same", Toast.LENGTH_SHORT).show();
       }

     }else {
         if (TextUtils.isEmpty(signupBinding.EmailSignup.getText())){
             signupBinding.EmailSignup.setError("Enter Your Email");

         }
         if (TextUtils.isEmpty(signupBinding.FullnameSignup.getText())){
             signupBinding.FullnameSignup.setError("Enter Your Full name");

         }
         if (TextUtils.isEmpty(signupBinding.passwordSignup.getText())){
             signupBinding.passwordSignup.setError("Enter Your password");

         }
         if (TextUtils.isEmpty(signupBinding.repasswordsignup.getText())){
             signupBinding.repasswordsignup.setError("Enter Your password");
         }
     }

    }
}