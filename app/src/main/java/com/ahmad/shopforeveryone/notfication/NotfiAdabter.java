package com.ahmad.shopforeveryone.notfication;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmad.shopforeveryone.Adapters.AdapterNotfication;
import com.ahmad.shopforeveryone.R;
import com.ahmad.shopforeveryone.authenticator.DataBase.RealTimeDataBaseManager;
import com.ahmad.shopforeveryone.databinding.DialogNotficationBinding;

import java.util.ArrayList;
import java.util.function.Consumer;

public class NotfiAdabter extends DialogFragment {
    DialogNotficationBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DialogNotficationBinding.inflate(inflater, container, false);
        //set transparent background for dialog
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setTitle("Notifications");
        LoadingDataScreen();
        RealTimeDataBaseManager realTimeDataBaseManager = new RealTimeDataBaseManager();

        realTimeDataBaseManager.getnotfiList(new Consumer<ArrayList<NotficationCustomClass>>() {
            @Override
            public void accept(ArrayList<NotficationCustomClass> notficationCustomClasses) {
                if (getContext() != null) {
                    binding.linerNotfi.removeAllViews();
                    AdapterNotfication NOTFI = new AdapterNotfication(requireContext(), notficationCustomClasses);
                    RecyclerView recyclerView = new RecyclerView(requireContext());
                    recyclerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
                    recyclerView.setAdapter(NOTFI);
                    binding.linerNotfi.addView(recyclerView);
                }

            }
        }, new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) {
                if (aBoolean) {
                    if (getContext() != null) {
                        binding.linerNotfi.removeAllViews();
                        //inflate text view and add it to liner layout
                        TextView textView = new TextView(requireContext());
                        textView.setText("No \n Notifications \nYet!");
                        textView.setTextColor(Color.WHITE);
                        textView.setGravity(Gravity.CENTER);
                        textView.setTextSize(34f);
                        binding.linerNotfi.setGravity(Gravity.CENTER);
                        binding.linerNotfi.addView(textView);
                        binding.linerNotfi.setFitsSystemWindows(true);
                    }


                }
            }
        });

        //dismiss screen if user press on cross
        binding.btnDismiss.setOnClickListener(v -> {
            realTimeDataBaseManager.Removenotfi();
            dismiss();
        });
        return binding.getRoot();
    }

    private void LoadingDataScreen() {
        ProgressBar progressBar = new ProgressBar(requireContext(), null, android.R.attr.progressBarStyle);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        progressBar.setLayoutParams(layoutParams);
        progressBar.getIndeterminateDrawable().setColorFilter(0xFFFFFFFF, android.graphics.PorterDuff.Mode.MULTIPLY);
        binding.linerNotfi.setGravity(Gravity.CENTER);
        binding.linerNotfi.addView(progressBar);


    }
}
