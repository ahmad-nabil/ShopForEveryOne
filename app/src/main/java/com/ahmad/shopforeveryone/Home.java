package com.ahmad.shopforeveryone;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;

import com.ahmad.shopforeveryone.Adapters.AdapterTapScreen;
import com.ahmad.shopforeveryone.FragmentsViewsHome.AllSections;
import com.ahmad.shopforeveryone.FragmentsViewsHome.KidsView;
import com.ahmad.shopforeveryone.FragmentsViewsHome.ManView;
import com.ahmad.shopforeveryone.FragmentsViewsHome.womanView;
import com.ahmad.shopforeveryone.SectionOrders.Models.SectionModels;
import com.ahmad.shopforeveryone.SectionOrders.checkout.Cart;
import com.ahmad.shopforeveryone.SectionOrders.checkout.orders;
import com.ahmad.shopforeveryone.SectionOrders.items.All;
import com.ahmad.shopforeveryone.SectionOrders.items.Kids;
import com.ahmad.shopforeveryone.SectionOrders.items.Man;
import com.ahmad.shopforeveryone.SectionOrders.items.Woman;
import com.ahmad.shopforeveryone.authenticator.Profile;
import com.ahmad.shopforeveryone.databinding.HomeBinding;
import com.ahmad.shopforeveryone.notfication.NotfiAdabter;
import com.google.android.material.tabs.TabLayoutMediator;

public class Home extends AppCompatActivity {

    AdapterTapScreen adapterTapScreen;
    HomeBinding homeBinding;
    SectionModels sectionModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeBinding = HomeBinding.inflate(getLayoutInflater());
        setContentView(homeBinding.getRoot());
        FirebaseNotficationService firebaseNotficationService = new FirebaseNotficationService();
        //initialize adapter tap
        adapterTapScreen = new AdapterTapScreen(getSupportFragmentManager(), getLifecycle());
        adapterTapScreen.addFragment(new womanView(), "Woman");
        adapterTapScreen.addFragment(new ManView(), "Man");
        adapterTapScreen.addFragment(new KidsView(), "Kids");
        adapterTapScreen.addFragment(new AllSections(), "All");
        //Add adapter to vpager
        homeBinding.viewpager.setAdapter(adapterTapScreen);

        new TabLayoutMediator(homeBinding.tapHome, homeBinding.viewpager, (tab, position) -> tab.setText(adapterTapScreen.getTitle(position))).attach();
        homeBinding.bottomNavigationView.setItemIconTintList(null);
//set listener with methods for every btn
        homeBinding.SeeMoreBtn.setOnClickListener(this::goToSection);
        homeBinding.ordercartBtn.setOnClickListener(this::goToCart);
        homeBinding.bottomNavigationView.setOnItemSelectedListener(this::BottomNavLayout);
        sectionModels = new ViewModelProvider(this).get(SectionModels.class);
//if user try search
        fetchSearch();
    }

    //go to cart screen
    private void goToCart(View view) {
        startActivity(new Intent(this, Cart.class));
    }

    //check data for search bar
    private void fetchSearch() {
        homeBinding.searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent search = new Intent(Home.this, searchActivity.class);
                search.putExtra("search", query);
                startActivity(search);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    //bottom navigation listener
    private boolean BottomNavLayout(MenuItem item) {
        if (item.getItemId() == R.id.profile) {
            startActivity(new Intent(Home.this, Profile.class));
            return true;
        } else if (item.getItemId() == R.id.orders) {
            startActivity(new Intent(Home.this, orders.class));
            return true;

        } else if (item.getItemId() == R.id.notfi) {
            NotfiAdabter notfiAdabter = new NotfiAdabter();
            notfiAdabter.show(getSupportFragmentManager(), "");
            return true;

        } else {
            return false;

        }
    }

    //check any screen you selected and go to it if press on see more btn
    private void goToSection(View view) {
        switch (homeBinding.tapHome.getSelectedTabPosition()) {
            case 0:
                startActivity(new Intent(this, Woman.class));
                break;
            case 1:
                startActivity(new Intent(this, Man.class));
                break;
            case 2:
                startActivity(new Intent(this, Kids.class));
                break;
            case 3:
                startActivity(new Intent(this, All.class));
                break;

        }
    }

}