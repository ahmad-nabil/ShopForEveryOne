package com.ahmad.shopforeveryone.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmad.shopforeveryone.SectionOrders.Models.CustomItemClass;
import com.ahmad.shopforeveryone.SectionOrders.Models.customSectionsObject;
import com.ahmad.shopforeveryone.SectionOrders.items.ItemClicked;
import com.ahmad.shopforeveryone.databinding.ItemsSectionsBinding;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterSections extends RecyclerView.Adapter<AdapterSections.HolderSectionOrder> {
    ArrayList<customSectionsObject> list;
    Context context;
    private ItemClicked mListener;

    public AdapterSections(ArrayList<customSectionsObject> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public HolderSectionOrder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemsSectionsBinding itemsSectionsBinding = ItemsSectionsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new HolderSectionOrder(itemsSectionsBinding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HolderSectionOrder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(list.get(position).getImgSrc()).into(holder.itemsSectionsBinding.itemsView);
        holder.itemsSectionsBinding.itemName.setText(list.get(position).getName());
        holder.itemsSectionsBinding.priceitem.setText(list.get(position).getPrice() + "\t" + "Jod");
        holder.itemsSectionsBinding.numItem.setText(String.valueOf(list.get(position).getCount()));
        holder.itemsSectionsBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int totalprice = list.get(position).getPrice() * list.get(position).getCount();
                CustomItemClass itemClass = new CustomItemClass(list.get(position).getName()
                        , list.get(position).getCount(), list.get(position).getPrice()
                        , totalprice, list.get(position).getImgSrc());
                if (mListener != null) {
                    mListener.onItemClick(itemClass);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HolderSectionOrder extends RecyclerView.ViewHolder {
        ItemsSectionsBinding itemsSectionsBinding;

        HolderSectionOrder(ItemsSectionsBinding binding) {
            super(binding.getRoot());
            itemsSectionsBinding = binding;
        }
    }

    public void setItemClicked(ItemClicked itemClicked) {
        this.mListener = itemClicked;
    }
}
