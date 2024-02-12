package com.ahmad.shopforeveryone.SectionOrders.checkout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmad.shopforeveryone.Adapters.AdapterCart;
import com.ahmad.shopforeveryone.Home;
import com.ahmad.shopforeveryone.R;
import com.ahmad.shopforeveryone.SectionOrders.Models.CustomItemClass;
import com.ahmad.shopforeveryone.SectionOrders.Models.CustomListBuyNow;
import com.ahmad.shopforeveryone.authenticator.DataBase.RealTimeDataBaseManager;
import com.ahmad.shopforeveryone.databinding.ActivityCartBinding;
import com.ahmad.shopforeveryone.databinding.CartEmptyBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.function.Consumer;

public class Cart extends AppCompatActivity {
ActivityCartBinding cartBinding;
RealTimeDataBaseManager realTimeDataBaseManager=new RealTimeDataBaseManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cartBinding=ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(cartBinding.getRoot());
        LoadingDataScreen();
        checkCart();

        cartBinding.toolbarCart.setNavigationOnClickListener(v->{
            startActivity(new Intent(Cart.this, Home.class));
            finish();
        });
    }

    private void checkCart() {

     realTimeDataBaseManager.getCartList(new Consumer<ArrayList<CustomItemClass>>() {
         @Override
         public void accept(ArrayList<CustomItemClass> customItemClasses) {
             cartBinding.FrameCart.removeAllViews();
             cartBinding.CheckOutBTN.setVisibility(View.VISIBLE);
             AdapterCart Cart=new AdapterCart(Cart.this,customItemClasses);
             RecyclerView recyclerView=new RecyclerView(Cart.this);
             recyclerView.setLayoutManager(new GridLayoutManager(Cart.this,2));
             recyclerView.setAdapter(Cart);
             recyclerView.setLayoutParams(new ViewGroup.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
             cartBinding.FrameCart.addView(recyclerView);
             cartBinding.CheckOutBTN.setOnClickListener(v->{
                 ArrayList<CustomListBuyNow>buynow=new ArrayList<>();
                 int numproduct=0;
                 int totalprice=0;
                 for (CustomItemClass data: customItemClasses) {
                     numproduct=numproduct+data.getCount();
                     totalprice=totalprice+data.getTotalprice();
                 }
                 buynow.add(new CustomListBuyNow(totalprice,numproduct));
                 Intent intent=new Intent(Cart.this, CheckOut.class);
             intent.putParcelableArrayListExtra("buynow",buynow);
                 startActivity(intent);
             });

         }
     }, new Consumer<Boolean>() {
         @Override
         public void accept(Boolean aBoolean) {
             if (aBoolean){
                 cartBinding.FrameCart.removeAllViews();
                 cartBinding.CheckOutBTN.setVisibility(View.GONE);
                 CartEmptyBinding cartEmptyBinding=CartEmptyBinding.inflate(getLayoutInflater());
                 cartBinding.FrameCart.addView(cartEmptyBinding.getRoot());
                 cartEmptyBinding.OrderNowBTNCart.setOnClickListener(v->{
                     startActivity(new Intent(Cart.this, Home.class));
                     finish();
                 });
             }
         }
     });

    }
    private void LoadingDataScreen(){
        LinearLayout Linear=new LinearLayout(this);
        ProgressBar progressBar=new ProgressBar(this,null, android.R.attr.progressBarStyle);
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(50,50,50,50);
        progressBar.setLayoutParams(layoutParams);
        Linear.addView(progressBar);
        TextView Loading=new TextView(this);
        Loading.setText("loading Cart");
        Loading.setTextSize(34f);
        Typeface typeface=Typeface.createFromAsset(getAssets(),"clearsans_bold.ttf");
        Loading.setTypeface(typeface);
        Linear.addView(Loading);
        Linear.setGravity(Gravity.CENTER);
        Linear.setOrientation(LinearLayout.VERTICAL);
        cartBinding.CheckOutBTN.setVisibility(View.GONE);
        cartBinding.FrameCart.addView(Linear);

    }
}