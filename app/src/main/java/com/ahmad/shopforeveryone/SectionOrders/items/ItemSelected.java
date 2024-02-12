package com.ahmad.shopforeveryone.SectionOrders.items;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.ahmad.shopforeveryone.SectionOrders.Models.CustomItemClass;
import com.ahmad.shopforeveryone.SectionOrders.Models.CustomListBuyNow;
import com.ahmad.shopforeveryone.SectionOrders.checkout.CheckOut;
import com.ahmad.shopforeveryone.authenticator.DataBase.RealTimeDataBaseManager;
import com.ahmad.shopforeveryone.databinding.FragmentItemSelectedBinding;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class ItemSelected extends Fragment {
    FragmentItemSelectedBinding itemSelectedBinding;
    CustomItemClass itemClass;
    int countitem;
    RealTimeDataBaseManager realTimeDataBaseManager = new RealTimeDataBaseManager();

    public static ItemSelected newInstance(Bundle bundle) {
        ItemSelected itemSelected = new ItemSelected();
        itemSelected.setArguments(bundle);
        return itemSelected;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        itemSelectedBinding = FragmentItemSelectedBinding.inflate(getLayoutInflater());
        Bundle args = getArguments();
        if (args != null) {
            itemClass = args.getParcelable("itemClicked");
            countitem = itemClass.getCount();
            itemSelectedBinding.countitems.setText(String.valueOf(countitem));
            itemSelectedBinding.nameitems.setText(itemClass.getItemname());
            itemSelectedBinding.totalprice.setText(String.valueOf(itemClass.getTotalprice()));

            ArrayList<SlideModel> slideModels = new ArrayList<>();
            slideModels.add(new SlideModel(itemClass.getImgsrc(), ScaleTypes.FIT));
            slideModels.add(new SlideModel(itemClass.getImgsrc(), ScaleTypes.FIT));
            itemSelectedBinding.slidesIMG.setImageList(slideModels);

        }
        itemSelectedBinding.BtnAdd.setOnClickListener(this::ADD);
        itemSelectedBinding.BtnRemove.setOnClickListener(this::Remove);
        itemSelectedBinding.buynowBtn.setOnClickListener(this::buynowManager);
        itemSelectedBinding.addCart.setOnClickListener(this::AddCartManager);
        return itemSelectedBinding.getRoot();
    }


    private void Remove(View view) {
        if (itemClass.getCount() > 0) {
            countitem--;
            itemSelectedBinding.countitems.setText(String.valueOf(countitem));
            int totalprice = itemClass.getPrice() * countitem;
            itemSelectedBinding.totalprice.setText(String.valueOf(totalprice));
            itemClass.setCount(countitem);
            itemClass.setTotalprice(totalprice);
        } else {
            Toast.makeText(requireContext(), "add one at Least", Toast.LENGTH_SHORT).show();
        }
    }

    private void ADD(View view) {
        countitem++;
        itemSelectedBinding.countitems.setText(String.valueOf(countitem));
        int totalprice = itemClass.getPrice() * countitem;
        itemSelectedBinding.totalprice.setText(String.valueOf(totalprice));
        itemClass.setCount(countitem);
        itemClass.setTotalprice(totalprice);
    }

    private void buynowManager(View BuyNowBtn) {
        //put value buy now true in shared preference  to avoid remove item from cart in last steps
        SharedPreferences buynowState = getActivity().getSharedPreferences("buynow", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = buynowState.edit();
        if (itemClass.getCount() > 0) {
            int totalprice = itemClass.getPrice() * countitem;
            ArrayList<CustomListBuyNow> buyNows = new ArrayList<>();
            buyNows.add(new CustomListBuyNow(totalprice, countitem));
            Intent intent = new Intent(requireContext(), CheckOut.class);
            intent.putParcelableArrayListExtra("buynow", buyNows);
            editor.putBoolean("buyNow", true).commit();
            startActivity(intent);
        } else {
            Toast.makeText(requireContext(), "add one at Least", Toast.LENGTH_SHORT).show();

        }
    }

    private void AddCartManager(View addCartBtn) {
        if (itemClass.getCount() > 0) {
            realTimeDataBaseManager.AddTocart(itemClass);
        } else {
            Toast.makeText(requireContext(), "add one at Least", Toast.LENGTH_SHORT).show();
        }
    }


}


