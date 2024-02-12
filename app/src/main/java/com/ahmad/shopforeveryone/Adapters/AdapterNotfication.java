package com.ahmad.shopforeveryone.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmad.shopforeveryone.databinding.NotficationlayoutBinding;
import com.ahmad.shopforeveryone.notfication.NotficationCustomClass;

import java.util.ArrayList;

public class AdapterNotfication extends RecyclerView.Adapter<AdapterNotfication.HolderNotfi> {
    Context context;
    ArrayList<NotficationCustomClass> list;

    public AdapterNotfication(Context context, ArrayList<NotficationCustomClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HolderNotfi onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NotficationlayoutBinding binding = NotficationlayoutBinding.inflate(LayoutInflater.from(context), parent, false);
        return new HolderNotfi(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderNotfi holder, int position) {
        holder.binding.bodynotfi.setText(list.get(position).getBody());
        holder.binding.titlenotfi.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HolderNotfi extends RecyclerView.ViewHolder {
        NotficationlayoutBinding binding;

        public HolderNotfi(@NonNull NotficationlayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
