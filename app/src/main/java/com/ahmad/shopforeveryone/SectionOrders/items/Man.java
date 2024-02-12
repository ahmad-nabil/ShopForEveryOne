package com.ahmad.shopforeveryone.SectionOrders.items;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.ahmad.shopforeveryone.Adapters.AdapterSections;
import com.ahmad.shopforeveryone.SectionOrders.Models.CustomItemClass;
import com.ahmad.shopforeveryone.SectionOrders.Models.SectionModels;
import com.ahmad.shopforeveryone.databinding.ActivityManBinding;

public class Man extends AppCompatActivity implements ItemClicked {
    SectionModels sectionModels;
    AdapterSections adabterSectionsMan;
    ActivityManBinding manBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manBinding = ActivityManBinding.inflate(getLayoutInflater());
        setContentView(manBinding.getRoot());
        sectionModels = new ViewModelProvider(this).get(SectionModels.class);
        sectionModels.getMan().observe(this, customClassItemsOpjects -> {
            adabterSectionsMan = new AdapterSections(customClassItemsOpjects, Man.this);
            adabterSectionsMan.setItemClicked(this);
            manBinding.recyclerViewMan.setLayoutManager(new GridLayoutManager(Man.this, 2));
            manBinding.recyclerViewMan.setAdapter(adabterSectionsMan);

        });
    }

    @Override
    public void onItemClick(CustomItemClass clickedItem) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("itemClicked", clickedItem);
        ItemSelected itemSelected = new ItemSelected();
        itemSelected.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .add(manBinding.FrameMan.getId(), itemSelected)
                .addToBackStack(null)
                .commit();

    }
}