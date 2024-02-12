package com.ahmad.shopforeveryone.SectionOrders.items;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.ahmad.shopforeveryone.Adapters.AdapterSections;
import com.ahmad.shopforeveryone.SectionOrders.Models.CustomItemClass;
import com.ahmad.shopforeveryone.SectionOrders.Models.SectionModels;
import com.ahmad.shopforeveryone.databinding.ActivityWomanBinding;

public class Woman extends AppCompatActivity implements ItemClicked {
    ActivityWomanBinding womanBinding;
    private AdapterSections adabterSectionsWoman;
    SectionModels sectionModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        womanBinding = ActivityWomanBinding.inflate(getLayoutInflater());
        setContentView(womanBinding.getRoot());
        sectionModels = new ViewModelProvider(this).get(SectionModels.class);
        sectionModels.getWoman().observe(this, customClassItemsOpjects -> {
            adabterSectionsWoman = new AdapterSections(customClassItemsOpjects, Woman.this);
            adabterSectionsWoman.setItemClicked(this);
            womanBinding.recyclerViewWoman.setLayoutManager(new GridLayoutManager(Woman.this, 2));
            womanBinding.recyclerViewWoman.setAdapter(adabterSectionsWoman);

        });
        womanBinding.materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Woman.super.onBackPressed();
            }
        });
    }

    @Override
    public void onItemClick(CustomItemClass clickedItem) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("itemClicked", clickedItem);
        ItemSelected itemSelected = ItemSelected.newInstance(bundle);
        getSupportFragmentManager().beginTransaction()
                .add(womanBinding.FrameWoman.getId(), itemSelected)
                .addToBackStack(null)
                .commit();

    }
}