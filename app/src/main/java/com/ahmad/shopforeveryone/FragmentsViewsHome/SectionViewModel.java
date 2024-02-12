package com.ahmad.shopforeveryone.FragmentsViewsHome;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahmad.shopforeveryone.R;

import java.util.ArrayList;
import java.util.Arrays;

public class SectionViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Integer>> Woman = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Integer>> Man = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Integer>> Kids = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Integer>> All = new MutableLiveData<>();


    public MutableLiveData<ArrayList<Integer>> getWoman() {
        ArrayList<Integer> womanImgSrc = new ArrayList<>(Arrays.asList(R.drawable.womanitems1, R.drawable.womanitems2, R.drawable.womanitems3, R.drawable.womanitems1, R.drawable.womanitems2, R.drawable.womanitems3));
        Woman.setValue(womanImgSrc);
        return Woman;
    }

    public MutableLiveData<ArrayList<Integer>> getMan() {
        ArrayList<Integer> manImgSrc = new ArrayList<>(Arrays.asList(R.drawable.manitems1, R.drawable.manitems2, R.drawable.manitems3, R.drawable.manitems1, R.drawable.manitems2, R.drawable.manitems3));
        Man.setValue(manImgSrc);
        return Man;
    }

    public MutableLiveData<ArrayList<Integer>> getKids() {
        ArrayList<Integer> kidsImgSrc = new ArrayList<>(Arrays.asList(R.drawable.kidsitems1, R.drawable.kidsitems2, R.drawable.kidsitems3, R.drawable.kidsitems1, R.drawable.kidsitems2, R.drawable.kidsitems3));
        Kids.setValue(kidsImgSrc);
        return Kids;
    }

    public MutableLiveData<ArrayList<Integer>> getAll() {
        ArrayList<Integer> AllImgSrc = new ArrayList<>(Arrays.asList(
                R.drawable.womanitems1, R.drawable.womanitems2, R.drawable.womanitems3, R.drawable.womanitems1, R.drawable.womanitems2, R.drawable.womanitems3,
                R.drawable.manitems1, R.drawable.manitems2, R.drawable.manitems3, R.drawable.manitems1, R.drawable.manitems2, R.drawable.manitems3
                , R.drawable.kidsitems1, R.drawable.kidsitems2, R.drawable.kidsitems3, R.drawable.kidsitems1, R.drawable.kidsitems2, R.drawable.kidsitems3));
        All.setValue(AllImgSrc);
        return All;
    }
}