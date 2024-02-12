package com.ahmad.shopforeveryone.FragmentsViewsHome;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmad.shopforeveryone.Adapters.AdapterViewHome;
import com.ahmad.shopforeveryone.databinding.FragmentKidsBinding;


public class KidsView extends Fragment {

    SectionViewModel viewModelKids;
    FragmentKidsBinding binding;
    AdapterViewHome slidesKides;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentKidsBinding.inflate(inflater, container, false);
        viewModelKids = new ViewModelProvider(this).get(SectionViewModel.class);
        viewModelKids.getKids().observe(getViewLifecycleOwner(), integers -> {

            slidesKides = new AdapterViewHome(integers, requireContext());
            binding.RecycleKids.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
            binding.RecycleKids.setAdapter(slidesKides);
        });
        return binding.getRoot();
    }
}