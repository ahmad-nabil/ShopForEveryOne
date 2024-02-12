package com.ahmad.shopforeveryone.SectionOrders.items;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.ahmad.shopforeveryone.Adapters.AdapterSections;
import com.ahmad.shopforeveryone.SectionOrders.Models.CustomItemClass;
import com.ahmad.shopforeveryone.SectionOrders.Models.SectionModels;
import com.ahmad.shopforeveryone.databinding.ActivityAllBinding;

public class All extends AppCompatActivity implements ItemClicked {
    ActivityAllBinding activityAllBinding;
    SectionModels sectionALL;
    AdapterSections adabterSectionsAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityAllBinding = ActivityAllBinding.inflate(getLayoutInflater());
        setContentView(activityAllBinding.getRoot());
        sectionALL = new ViewModelProvider(this).get(SectionModels.class);
        sectionALL.getAll().observe(this, customClassItemsOpjects -> {
            adabterSectionsAll = new AdapterSections(customClassItemsOpjects, All.this);
            activityAllBinding.RVALL.setLayoutManager(new GridLayoutManager(All.this, 2));
            activityAllBinding.RVALL.setAdapter(adabterSectionsAll);
            adabterSectionsAll.setItemClicked(this);
        });
    }

    @Override
    public void onItemClick(CustomItemClass clickedItem) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("itemClicked", clickedItem);
        ItemSelected itemSelected = new ItemSelected();
        itemSelected.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .add(activityAllBinding.FrameAll.getId(), itemSelected)
                .addToBackStack(null)
                .commit();
    }
}