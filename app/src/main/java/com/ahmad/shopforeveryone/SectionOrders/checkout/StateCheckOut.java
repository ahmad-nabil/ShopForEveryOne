package com.ahmad.shopforeveryone.SectionOrders.checkout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ahmad.shopforeveryone.Home;
import com.ahmad.shopforeveryone.R;
import com.ahmad.shopforeveryone.SectionOrders.Models.CustomListBuyNow;
import com.ahmad.shopforeveryone.SectionOrders.Models.CustomOrdersList;
import com.ahmad.shopforeveryone.authenticator.DataBase.RealTimeDataBaseManager;
import com.ahmad.shopforeveryone.databinding.FragmentStateCheckOutBinding;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class StateCheckOut extends Fragment {
FragmentStateCheckOutBinding stateCheckOutBinding;
    LinearLayout.LayoutParams params;
    Typeface customfont;
    RealTimeDataBaseManager realTimeDataBaseManager=new RealTimeDataBaseManager();
  private   boolean fromCart=true;
     public static StateCheckOut newInstance(Bundle bundle) {
        StateCheckOut fragment = new StateCheckOut();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         stateCheckOutBinding=FragmentStateCheckOutBinding.inflate(inflater,container,false);
Bundle bundle=getArguments();
fromCart=bundle.getBoolean("fromCart");

//initilze some component to add UI programmeticaly
InitiComponents();
//check state order and add it progemmeticaly
        stateOrder(bundle.getBoolean("state"));
//listener static btn to home
stateCheckOutBinding.HomeBtn.setOnClickListener(v->{
    startActivity(new Intent(requireContext(), Home.class));
    requireActivity().finish();
});
        return stateCheckOutBinding.getRoot();
    }

    private void stateOrder(boolean state) {
        ImageView StateOrderIV=new ImageView(requireContext());
        StateOrderIV.setLayoutParams(params);
        TextView stateorders=new TextView(requireContext());
        TextView orderid=new TextView(requireContext());
        stateorders.setTextColor(Color.rgb(147,143,143));
        stateorders.setLayoutParams(params);
        stateorders.setTextSize(18f);
        stateorders.setTypeface(customfont);
        orderid.setTypeface(customfont);
        orderid.setTextColor(Color.rgb(21,77,160));
        orderid.setTextSize(18f);
        stateorders.setGravity(Gravity.CENTER);
        orderid.setGravity(Gravity.CENTER);
        if (state){
            Glide.with(requireContext()).load(R.drawable.success).into(StateOrderIV);
            stateorders.setText("Order Completed");
            String productid=realTimeDataBaseManager.generateOrderId();
            orderid.setText("Your Order ID:\n #"+productid+"\nThank You!");
            Bundle bundle=getArguments();
            CustomListBuyNow listBuyNow=bundle.getParcelable("order");
            CustomOrdersList list=new CustomOrdersList(productid,String.valueOf(listBuyNow.getNumproduct()),String.valueOf(listBuyNow.getTotalpriceproduct()));
            realTimeDataBaseManager.AddToOrdersList(list);
            //check if from Cart or button buy now
            SharedPreferences getValue= getActivity().getSharedPreferences("buynow", Context.MODE_PRIVATE);

            if (getValue.getBoolean("buyNow",false)){
                SharedPreferences.Editor editor=getValue.edit();
                editor.remove("buyNow").commit();
            }else {
                realTimeDataBaseManager.RemoveCart();

            }

        }else{
            Glide.with(requireContext()).load(R.drawable.crossred).into(StateOrderIV);
            stateorders.setText("Order failed");
            orderid.setText("Please check your information and\n try again.");
        }
        stateCheckOutBinding.state.addView(StateOrderIV);
        stateCheckOutBinding.state.addView(stateorders);
        stateCheckOutBinding.state.addView(orderid);
    }

    private void InitiComponents(){
params=new LinearLayout.LayoutParams(    ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(20, 30, 20, 30);

     customfont=Typeface.createFromAsset(getActivity().getAssets(),"clearsans_bold.ttf");

    }

}