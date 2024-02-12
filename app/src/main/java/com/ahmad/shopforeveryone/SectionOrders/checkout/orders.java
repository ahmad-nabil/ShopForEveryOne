package com.ahmad.shopforeveryone.SectionOrders.checkout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ahmad.shopforeveryone.Adapters.AdapterOrders;
import com.ahmad.shopforeveryone.Home;
import com.ahmad.shopforeveryone.R;
import com.ahmad.shopforeveryone.SectionOrders.Models.CustomListBuyNow;
import com.ahmad.shopforeveryone.SectionOrders.Models.CustomOrdersList;
import com.ahmad.shopforeveryone.authenticator.DataBase.RealTimeDataBaseManager;
import com.ahmad.shopforeveryone.databinding.ActivityOrdersBinding;
import com.ahmad.shopforeveryone.databinding.OrdersEmptyBinding;
import com.ahmad.shopforeveryone.databinding.Searchresult0Binding;

import java.util.ArrayList;
import java.util.function.Consumer;

public class orders extends AppCompatActivity {
ActivityOrdersBinding ordersBinding;
ArrayList <CustomListBuyNow>list;
RealTimeDataBaseManager realTimeDataBaseManager=new RealTimeDataBaseManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ordersBinding=ActivityOrdersBinding.inflate(getLayoutInflater());
        setContentView(ordersBinding.getRoot());
        LoadingDataScreen();
        CheckOrdersInDatabase();
        ordersBinding.toolbarOrders.setNavigationOnClickListener(v->{
            startActivity(new Intent(orders.this, Home.class));
            finish();
        });
    }

    private void CheckOrdersInDatabase() {
        realTimeDataBaseManager.getOrderList(new Consumer<ArrayList<CustomOrdersList>>() {
            @Override
            public void accept(ArrayList<CustomOrdersList> CustomOrdersList) {
                ordersBinding.FrameOrders.removeAllViews();
                RecyclerView recyclerView = new RecyclerView(orders.this);
                AdapterOrders adapterOrders = new AdapterOrders(CustomOrdersList, orders.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(orders.this, LinearLayoutManager.VERTICAL, false));
                recyclerView.setAdapter(adapterOrders);
                recyclerView.setLayoutParams(new ViewGroup.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                ordersBinding.FrameOrders.addView(recyclerView);
            }
        }, new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) {
                if (aBoolean){
                    ordersBinding.FrameOrders.removeAllViews();
                    OrdersEmptyBinding ordersEmptyBinding=OrdersEmptyBinding.inflate(getLayoutInflater());
                    ordersBinding.FrameOrders.addView(ordersEmptyBinding.getRoot());
                    ordersEmptyBinding.OrderNowBTN.setOnClickListener(v->{
                        startActivity(new Intent(orders.this, Home.class));
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
        Loading.setText("loading orders");
        Loading.setTextSize(34f);
        Typeface typeface=Typeface.createFromAsset(getAssets(),"clearsans_bold.ttf");
        Loading.setTypeface(typeface);
        Linear.addView(Loading);
        Linear.setGravity(Gravity.CENTER);
        Linear.setOrientation(LinearLayout.VERTICAL);
        ordersBinding.FrameOrders.addView(Linear);

    }
}