    package com.ahmad.shopforeveryone.Adapters;

    import android.annotation.SuppressLint;
    import android.content.Context;
    import android.view.LayoutInflater;
    import android.view.ViewGroup;

    import androidx.annotation.NonNull;
    import androidx.recyclerview.widget.RecyclerView;

    import com.ahmad.shopforeveryone.databinding.ItemsViewHomeBinding;
    import com.bumptech.glide.Glide;

    import java.util.ArrayList;

    public class AdapterViewHome extends RecyclerView.Adapter<AdapterViewHome.HolderHome> {
    ArrayList <Integer>imgSource;
    Context context;

        public AdapterViewHome(ArrayList<Integer> imgSource, Context context) {
            this.imgSource = imgSource;
            this.context = context;
        }

        @NonNull
        @Override
        public HolderHome onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
           ItemsViewHomeBinding itemsViewHomeBinding=ItemsViewHomeBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
            return new HolderHome(itemsViewHomeBinding);
        }


        @SuppressLint("CheckResult")
        @Override
        public void onBindViewHolder(@NonNull HolderHome holder, int position) {
            Glide.with(context).load(imgSource.get(position)).into(holder.itemsViewHomeBinding.slideimg);
        }

        @Override
        public int getItemCount() {
            return imgSource.size();
        }

        public class HolderHome extends RecyclerView.ViewHolder {
            ItemsViewHomeBinding itemsViewHomeBinding;
            public HolderHome(@NonNull ItemsViewHomeBinding binding) {
                super(binding.getRoot());
                this.itemsViewHomeBinding=binding;
            }
        }
    }
