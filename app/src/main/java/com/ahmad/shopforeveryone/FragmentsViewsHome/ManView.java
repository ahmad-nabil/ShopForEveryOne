package com.ahmad.shopforeveryone.FragmentsViewsHome;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmad.shopforeveryone.Adapters.AdapterViewHome;
import com.ahmad.shopforeveryone.databinding.FragmentManBinding;

public class ManView extends Fragment {
    SectionViewModel viewModelMan;
    AdapterViewHome slidesMan;
    FragmentManBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentManBinding.inflate(inflater, container, false);
        viewModelMan = new ViewModelProvider(this).get(SectionViewModel.class);
        viewModelMan.getMan().observe(getViewLifecycleOwner(), integers -> {
            slidesMan = new AdapterViewHome(integers, requireContext());
            binding.RecycleMan.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
            binding.RecycleMan.setAdapter(slidesMan);
        });

        return binding.getRoot();
    }
}