package com.ahmad.shopforeveryone.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmad.shopforeveryone.SectionOrders.Models.CustomOrdersList;
import com.ahmad.shopforeveryone.authenticator.DataBase.RealTimeDataBaseManager;
import com.ahmad.shopforeveryone.databinding.OrderlistBinding;

import java.util.ArrayList;

public class AdapterOrders extends RecyclerView.Adapter<AdapterOrders.holderOrders> {
    ArrayList<CustomOrdersList> CustomOrdersList;
    Context context;
    RealTimeDataBaseManager dataBaseManager = new RealTimeDataBaseManager();

    public AdapterOrders(ArrayList<CustomOrdersList> customListBuyNows, Context context) {
        this.CustomOrdersList = customListBuyNows;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterOrders.holderOrders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        OrderlistBinding orderlistBinding = OrderlistBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new holderOrders(orderlistBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterOrders.holderOrders holder, int position) {
        holder.orderlistBinding.numProductTV.setText(CustomOrdersList.get(position).getNumProduct() + "items");
        holder.orderlistBinding.finalpriceTV.setText(CustomOrdersList.get(position).getTotalPrice() + "jod");
        holder.orderlistBinding.deleteOrderBtn.setOnClickListener(v -> {
            dataBaseManager.RemoveOrder(CustomOrdersList.get(position).getProductid());
            notifyItemRemoved(position);
        });
    }

    @Override
    public int getItemCount() {
        return CustomOrdersList.size();
    }

    public class holderOrders extends RecyclerView.ViewHolder {
        OrderlistBinding orderlistBinding;

        public holderOrders(@NonNull OrderlistBinding listBinding) {
            super(listBinding.getRoot());
            orderlistBinding = listBinding;
        }
    }
}
