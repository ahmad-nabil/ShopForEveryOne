package com.ahmad.shopforeveryone.SectionOrders.checkout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmad.shopforeveryone.R;
import com.ahmad.shopforeveryone.SectionOrders.Models.CustomListBuyNow;
import com.ahmad.shopforeveryone.databinding.ActivityCheckOutBinding;
import com.ahmad.shopforeveryone.databinding.FragmentPaymentBinding;


public class payment extends Fragment {
FragmentPaymentBinding paymentBinding;
CustomListBuyNow listBuyNow;
boolean onDoor=false;

    public static payment newInstance(Bundle bundle) {
        payment fragment = new payment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        paymentBinding=FragmentPaymentBinding.inflate(inflater,container,false);
changeRadioBtnColor();
Bundle bundle=getArguments();
listBuyNow=bundle.getParcelable("buynow");
onDoor=bundle.getBoolean("onDoor");
if (onDoor){
    paymentBinding.Doordeliverypayment.setChecked(true);

}else {
    paymentBinding.pickuppayment.setChecked(true);

}
paymentBinding.TOTALPRICEPAYMENT.setText(listBuyNow.getTotalpriceproduct()+"\t JOD");
paymentBinding.ProceedtopaymentBTNpAY.setOnClickListener(this::checkoutfinal);


        return paymentBinding.getRoot();
    }

    private void checkoutfinal(View view) {
        //to get frame parent id
        ActivityCheckOutBinding binding=ActivityCheckOutBinding.inflate(getLayoutInflater());

        Bundle bundle=new Bundle();

        if (paymentBinding.CASH.isChecked()){
            //add state order as boolean
            bundle.putBoolean("state",true);
            bundle.putParcelable("order",listBuyNow);
            StateCheckOut stateCheckOut=new StateCheckOut();
            stateCheckOut.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .add(binding.FramCheckout.getId(),stateCheckOut).addToBackStack(null).commit();
       }else if (paymentBinding.CARD.isChecked()){
            PaymentPaypal paymentPaypal=new PaymentPaypal();
            bundle.putParcelable("order",listBuyNow);
            paymentPaypal.setArguments(bundle);
            paymentPaypal.amount=String.valueOf(listBuyNow.getTotalpriceproduct());
            getActivity().getSupportFragmentManager().beginTransaction()
                    .add(binding.FramCheckout.getId(),paymentPaypal).addToBackStack(null).commit();
        }

    }

    private void changeRadioBtnColor() {

        ColorStateList stateList=new ColorStateList(
                new int[][]{
                        new int[]{-android.R.attr.state_enabled},
                        new int[]{android.R.attr.state_enabled}
                },new int[]{
                ViewCompat.MEASURED_STATE_MASK, Color.rgb(255, 87, 87)
        }
        );
        paymentBinding.CARD.setButtonTintList(stateList);
        paymentBinding.CASH.setButtonTintList(stateList);
        paymentBinding.Doordeliverypayment.setButtonTintList(stateList);
        paymentBinding.pickuppayment.setButtonTintList(stateList);

    }
}