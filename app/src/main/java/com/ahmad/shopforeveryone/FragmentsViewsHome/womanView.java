package com.ahmad.shopforeveryone.FragmentsViewsHome;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmad.shopforeveryone.Adapters.AdapterViewHome;
import com.ahmad.shopforeveryone.databinding.FragmentWomanBinding;


public class womanView extends Fragment {
    FragmentWomanBinding womanBinding;
    AdapterViewHome slidesWoman;
    SectionViewModel viewModelWoman;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        womanBinding = FragmentWomanBinding.inflate(getLayoutInflater());
        viewModelWoman = new ViewModelProvider(this).get(SectionViewModel.class);
        viewModelWoman.getWoman().observe(getViewLifecycleOwner(), integers -> {
            slidesWoman = new AdapterViewHome(integers, requireContext());
            womanBinding.RecycleWoman.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
            womanBinding.RecycleWoman.setAdapter(slidesWoman);
        });
        return womanBinding.getRoot();
    }
}