package com.ahmad.shopforeveryone.authenticator.forgetpassword;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ahmad.shopforeveryone.Adapters.AdapterTapScreen;
import com.ahmad.shopforeveryone.databinding.ActivityForgetpasswordOperationBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class ForgetPasswordManager extends AppCompatActivity implements EmailResultListener {
ActivityForgetpasswordOperationBinding forgetpasswordOperationBinding;
AdapterTapScreen adaptertapScreen;
    verfictionCode verfictionCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        forgetpasswordOperationBinding=ActivityForgetpasswordOperationBinding.inflate(getLayoutInflater());
        setContentView(forgetpasswordOperationBinding.getRoot());
        //initialize AdapterNotfication tap
        adaptertapScreen=new AdapterTapScreen(getSupportFragmentManager(),getLifecycle());
        //initialize fragment
        EnterEmail enterEmail=new EnterEmail();
          verfictionCode =new verfictionCode();
          //set listener for interface
          verfictionCode.setEmailListener(this);
          enterEmail.setEmailListener(this);

        adaptertapScreen.addFragment(enterEmail,"Forget Password");
        forgetpasswordOperationBinding.vPagerForgetPassword.setAdapter(adaptertapScreen);
        new TabLayoutMediator(forgetpasswordOperationBinding.tablayoutforget,forgetpasswordOperationBinding.vPagerForgetPassword
        ,(tab, position) -> tab.setText(adaptertapScreen.getTitle(position))).attach();


    }

    @Override
    public void EmailResult(Boolean ValueEmail) {
if (ValueEmail){
    adaptertapScreen.updateFragment(0,verfictionCode);
    forgetpasswordOperationBinding.vPagerForgetPassword.setAdapter(adaptertapScreen);
}
    }

    @Override
    public void CodeResult(Boolean ValueCode,String Code,String UID) {
        if (ValueCode){
            RestPassword restPassword=new RestPassword();
            Bundle bundle=new Bundle();
            bundle.putString("code",Code);
            bundle.putString("UID",UID);
            restPassword.setArguments(bundle);
            adaptertapScreen.updateFragment(0,restPassword);
            forgetpasswordOperationBinding.vPagerForgetPassword.setAdapter(adaptertapScreen);
        }
    }
}