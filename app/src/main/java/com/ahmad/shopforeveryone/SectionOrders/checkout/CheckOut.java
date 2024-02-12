package com.ahmad.shopforeveryone.SectionOrders.checkout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.ahmad.shopforeveryone.R;
import com.ahmad.shopforeveryone.SectionOrders.Models.CustomListBuyNow;
import com.ahmad.shopforeveryone.databinding.ActivityCheckOutBinding;

import java.util.ArrayList;

public class CheckOut extends AppCompatActivity {
ActivityCheckOutBinding checkOutBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkOutBinding=ActivityCheckOutBinding.inflate(getLayoutInflater());
        setContentView(checkOutBinding.getRoot());
        ChooseDelivery();
    }

    private void ChooseDelivery() {
        ArrayList<CustomListBuyNow> buyNows = getIntent().getParcelableArrayListExtra("buynow");
       delivery delivery=new delivery();
       Bundle bundle=new Bundle();
       int totalPrice=0,itemCount=0;
        for (CustomListBuyNow buyNow : buyNows) {
            totalPrice = buyNow.getTotalpriceproduct();
             itemCount = buyNow.getNumproduct();
        }

        Toast.makeText(this, "e"+totalPrice+itemCount, Toast.LENGTH_SHORT).show();
        CustomListBuyNow listBuyNow=new CustomListBuyNow(totalPrice,itemCount);
        bundle.putParcelable("buynow",listBuyNow);

        delivery.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(checkOutBinding.FramCheckout.getId(),delivery).commit();
    }

    @Override
    public void onBackPressed() {
        Fragment fragment=getSupportFragmentManager().findFragmentById(checkOutBinding.FramCheckout.getId());
        if (fragment instanceof delivery||fragment instanceof payment||
                fragment instanceof PaymentPaypal||fragment instanceof StateCheckOut){
            Toast.makeText(this, "back is blocked", Toast.LENGTH_SHORT).show();
        }else {
            super.onBackPressed();
        }
    }
}