package com.ahmad.shopforeveryone.SectionOrders.items;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.ahmad.shopforeveryone.Adapters.AdapterSections;
import com.ahmad.shopforeveryone.SectionOrders.Models.CustomItemClass;
import com.ahmad.shopforeveryone.SectionOrders.Models.SectionModels;
import com.ahmad.shopforeveryone.databinding.ActivityKidsBinding;

public class Kids extends AppCompatActivity implements ItemClicked {
    SectionModels sectionKids;
    ActivityKidsBinding activitykidsBinding;
    AdapterSections adabterSectionsKids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activitykidsBinding = ActivityKidsBinding.inflate(getLayoutInflater());
        setContentView(activitykidsBinding.getRoot());

        sectionKids = new ViewModelProvider(this).get(SectionModels.class);
        sectionKids.getKids().observe(this, customClassItemsOpjects -> {
            adabterSectionsKids = new AdapterSections(customClassItemsOpjects, Kids.this);
            adabterSectionsKids.setItemClicked(this);
            activitykidsBinding.RVKids.setLayoutManager(new GridLayoutManager(Kids.this, 2));
            activitykidsBinding.RVKids.setAdapter(adabterSectionsKids);
        });
    }

    @Override
    public void onItemClick(CustomItemClass clickedItem) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("itemClicked", clickedItem);
        ItemSelected itemSelected = new ItemSelected();
        itemSelected.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .add(activitykidsBinding.kidsFrame.getId(), itemSelected)
                .addToBackStack(null)
                .commit();
    }
}