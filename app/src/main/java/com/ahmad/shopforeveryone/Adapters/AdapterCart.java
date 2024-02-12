package com.ahmad.shopforeveryone.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmad.shopforeveryone.SectionOrders.Models.CustomItemClass;
import com.ahmad.shopforeveryone.authenticator.DataBase.RealTimeDataBaseManager;
import com.ahmad.shopforeveryone.databinding.CartGridsBinding;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterCart extends RecyclerView.Adapter<AdapterCart.HolderCart> {
    Context context;
    ArrayList<CustomItemClass> List;
    RealTimeDataBaseManager realTimeDataBase;

    public AdapterCart(Context context, ArrayList<CustomItemClass> list) {
        this.context = context;
        List = list;
        realTimeDataBase = new RealTimeDataBaseManager();
    }

    @NonNull
    @Override
    public HolderCart onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CartGridsBinding cartGrids = CartGridsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new HolderCart(cartGrids);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderCart holder, int position) {
        holder.cartGridsBinding.itemNameCART.setText(List.get(position).getItemname());
        holder.cartGridsBinding.priceitemCART.setText(List.get(position).getTotalprice() + "\t" + "jod");
        holder.cartGridsBinding.numItem.setText(String.valueOf(List.get(position).getCount()));
        Glide.with(context).load(List.get(position).getImgsrc()).into(holder.cartGridsBinding.itemsView);
        holder.cartGridsBinding.BtnAddcart.setOnClickListener(v -> {
            int count = List.get(position).getCount();
            count++;
            List.get(position).setCount(count);
            int totalprice = count * List.get(position).getPrice();
            List.get(position).setTotalprice(totalprice);
            holder.cartGridsBinding.numItem.setText(String.valueOf(List.get(position).getCount()));
            holder.cartGridsBinding.priceitemCART.setText(totalprice + "\t" + "jod");
            String itemname = List.get(position).getItemname();
            int price = List.get(position).getPrice();
            int imgsrc = List.get(position).getImgsrc();
            CustomItemClass customItemClass = new CustomItemClass(itemname, count,
                    price, totalprice, imgsrc);
            realTimeDataBase.AddTocart(customItemClass);
        });
        holder.cartGridsBinding.BtnRemovecart.setOnClickListener(s -> {

            int count = List.get(position).getCount();
            if (count > 1) {
                count--;
                List.get(position).setCount(count);
                int totalprice = count * List.get(position).getPrice();
                List.get(position).setTotalprice(totalprice);
                holder.cartGridsBinding.numItem.setText(String.valueOf(List.get(position).getCount()));
                holder.cartGridsBinding.priceitemCART.setText(totalprice + "\t" + "jod");
                String itemname = List.get(position).getItemname();
                int price = List.get(position).getPrice();
                int imgsrc = List.get(position).getImgsrc();
                CustomItemClass customItemClass = new CustomItemClass(itemname, count,
                        price, totalprice, imgsrc);
                realTimeDataBase.AddTocart(customItemClass);

            } else {

                String itemname = List.get(position).getItemname();
                realTimeDataBase.RemoveItemFromCart(itemname);
                notifyItemRemoved(position);
            }


        });
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public class HolderCart extends RecyclerView.ViewHolder {
        CartGridsBinding cartGridsBinding;

        public HolderCart(@NonNull CartGridsBinding Binding) {
            super(Binding.getRoot());
            cartGridsBinding = Binding;
        }
    }
}
