package com.ahmad.shopforeveryone;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmad.shopforeveryone.Adapters.AdapterSections;
import com.ahmad.shopforeveryone.SectionOrders.Models.SectionModels;
import com.ahmad.shopforeveryone.SectionOrders.Models.customSectionsObject;
import com.ahmad.shopforeveryone.databinding.ActivitySearchBinding;
import com.ahmad.shopforeveryone.databinding.Searchresult0Binding;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class searchActivity extends AppCompatActivity {
    String query;
    ActivitySearchBinding searchBinding;
    SectionModels sectionViewModel;
    AdapterSections adapterResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchBinding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(searchBinding.getRoot());
        //get All section model
        sectionViewModel = new ViewModelProvider(this).get(SectionModels.class);
        //get query user inserted it in home screen
        query = getIntent().getStringExtra("search");
        //operation to check if we have these data or not
        checkResult(query);
        searchBinding.search.setQuery(query, true);
//if user searching in another thing
        SearchOperation();
//if user pressed on nave back to previous layout
        searchBinding.toolbarsearch.setNavigationOnClickListener(v -> {
            super.onBackPressed();
        });
    }

    private void SearchOperation() {
        searchBinding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                checkResult(query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void checkResult(String query) {
        AtomicInteger numResult = new AtomicInteger();
        ArrayList<customSectionsObject> Result = new ArrayList<>();

        sectionViewModel.getAll().observe(this, list -> {

            for (customSectionsObject i : list) {
                if (i.getName().toLowerCase().contains(query.toLowerCase())) {
                    Result.add(i);
                    numResult.getAndIncrement();
                }
            }
            if (Result.size() > 0) {
                searchBinding.FrameSearchactvity.removeAllViews();
                searchBinding.ResultTV.setText("Found\t " + numResult + "\t results");
                RecyclerView ResultsView = new RecyclerView(this);
                adapterResult = new AdapterSections(Result, this);
                ResultsView.setLayoutManager(new GridLayoutManager(this, 2));
                ResultsView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT));
                ResultsView.setAdapter(adapterResult);
                numResult.set(0);
                searchBinding.FrameSearchactvity.addView(ResultsView);

            } else {
                searchBinding.FrameSearchactvity.removeAllViews();
                searchBinding.ResultTV.setText("Found\t " + numResult + "\t results");
                Searchresult0Binding searchresult0 = Searchresult0Binding.inflate(getLayoutInflater());
                searchBinding.FrameSearchactvity.addView(searchresult0.getRoot());
            }
        });
    }
}