package com.ahmad.shopforeveryone.FragmentsViewsHome;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmad.shopforeveryone.Adapters.AdapterViewHome;
import com.ahmad.shopforeveryone.databinding.FragmentAllBinding;

public class AllSections extends Fragment {
    SectionViewModel viewModelAll;
    FragmentAllBinding binding;
    AdapterViewHome slidesAll;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAllBinding.inflate(inflater, container, false);
        viewModelAll = new ViewModelProvider(this).get(SectionViewModel.class);
        viewModelAll.getAll().observe(getViewLifecycleOwner(), integers -> {
            slidesAll = new AdapterViewHome(integers, requireContext());
            binding.RecycleAll.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
            binding.RecycleAll.setAdapter(slidesAll);
        });

        return binding.getRoot();
    }
}