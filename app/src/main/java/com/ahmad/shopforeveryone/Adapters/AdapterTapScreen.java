package com.ahmad.shopforeveryone.Adapters;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class AdapterTapScreen extends FragmentStateAdapter {
    private final List<Fragment> fragments=new ArrayList<>();
    private final List<String> fragmentsTitle=new ArrayList<>();
    public AdapterTapScreen(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public AdapterTapScreen(@NonNull Fragment fragment) {
        super(fragment);
    }

    public AdapterTapScreen(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }
public void addFragment(Fragment fragment,String title){
        fragments.add(fragment);
        fragmentsTitle.add(title);
}
    @SuppressLint("NotifyDataSetChanged")
    public void updateFragment(int position, Fragment fragment) {
        fragments.set(position,fragment);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
    public CharSequence getTitle(int position){
        return fragmentsTitle.get(position);
    }
}
