package com.ahmad.shopforeveryone.SectionOrders.checkout;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;

import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ahmad.shopforeveryone.R;
import com.ahmad.shopforeveryone.SectionOrders.Models.CustomListBuyNow;
import com.ahmad.shopforeveryone.authenticator.DataBase.DataUser;
import com.ahmad.shopforeveryone.authenticator.Profilemodel;
import com.ahmad.shopforeveryone.databinding.ActivityCheckOutBinding;
import com.ahmad.shopforeveryone.databinding.FragmentDeliveryBinding;

public class delivery extends Fragment {
FragmentDeliveryBinding deliveryBinding;
CustomListBuyNow listBuyNow;
Profilemodel profilemodel;

    public static delivery newInstance(Bundle bundle) {
        delivery fragment = new delivery();
        fragment.setArguments(bundle);
        return fragment;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        deliveryBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_delivery, container, false);
        profilemodel = new ViewModelProvider(this).get(Profilemodel.class);
        deliveryBinding.setDataaDelivery(profilemodel);
        deliveryBinding.setLifecycleOwner(getViewLifecycleOwner());


        // Retrieve list data from arguments
        Bundle arg = getArguments();
        listBuyNow = arg.getParcelable("buynow");

        // change total price in UI
        deliveryBinding.totalpriceDe.setText(listBuyNow.getTotalpriceproduct() + "\t Jod");

        //change color radio btn
        changeRadioBtnColor();

        //check value to next screen
        deliveryBinding.ProceedtopaymentBTN.setOnClickListener(this::CheckDataUser);
        return deliveryBinding.getRoot();
    }
    private void CheckDataUser(View view) {
            if (TextUtils.isEmpty(deliveryBinding.address.getText()) ||TextUtils.isEmpty(deliveryBinding.numphone.getText())){
                Toast.makeText(requireContext(), "add all your details please", Toast.LENGTH_SHORT).show();
            }else {
                proceedtopayment();

        };
    }


    private void proceedtopayment() {
        //to get frame id
        ActivityCheckOutBinding binding=ActivityCheckOutBinding.inflate(getLayoutInflater());
        //Check if user choose Type Delivery
        if(deliveryBinding.Doordelivery.isChecked()||deliveryBinding.pickup.isChecked()){
            Bundle bundle=new Bundle();
            bundle.putParcelable("buynow",listBuyNow);
            bundle.putBoolean("onDoor",deliveryBinding.Doordelivery.isChecked());
            payment payment=new payment();
            payment.setArguments(bundle);

            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(binding.FramCheckout.getId(),payment).addToBackStack(null).commit();
        }else {
            Toast.makeText(requireContext(), "choose type delivery", Toast.LENGTH_SHORT).show();
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
        deliveryBinding.Doordelivery.setButtonTintList(stateList);
        deliveryBinding.pickup.setButtonTintList(stateList);
        deliveryBinding.Doordelivery.invalidate();
        deliveryBinding.pickup.invalidate();
    }

}