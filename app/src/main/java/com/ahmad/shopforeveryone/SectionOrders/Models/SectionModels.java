package com.ahmad.shopforeveryone.SectionOrders.Models;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahmad.shopforeveryone.R;

import java.util.ArrayList;

public class SectionModels extends ViewModel {
    private MutableLiveData<ArrayList<customSectionsObject>> Woman = new MutableLiveData<>();
    private MutableLiveData<ArrayList<customSectionsObject>> Man = new MutableLiveData<>();
    private MutableLiveData<ArrayList<customSectionsObject>> Kids = new MutableLiveData<>();
    private MutableLiveData<ArrayList<customSectionsObject>> All = new MutableLiveData<>();




    public MutableLiveData<ArrayList<customSectionsObject>> getWoman() {
        ArrayList<customSectionsObject> womanImgSrc = new ArrayList<>();
        womanImgSrc.add(new customSectionsObject(R.drawable.womanitems1,"woman jacket",15,0));
        womanImgSrc.add(new customSectionsObject(R.drawable.womanitems2,"Woman dress",15,0));
        womanImgSrc.add(new customSectionsObject(R.drawable.womanitems3,"Woman coat",15,0));
        womanImgSrc.add(new customSectionsObject(R.drawable.womanitems1,"Woman jacket",15,0));
        womanImgSrc.add(new customSectionsObject(R.drawable.womanitems2,"Woman dress",15,0));
        womanImgSrc.add(new customSectionsObject(R.drawable.womanitems3,"Woman coat",15,0));
        Woman.setValue(womanImgSrc);
        return Woman;
    }

    public MutableLiveData<ArrayList<customSectionsObject>> getMan() {
        ArrayList<customSectionsObject> manImgSrc = new ArrayList<>();
        manImgSrc.add(new customSectionsObject(R.drawable.manitems1,"Hoodie",15,0));
        manImgSrc.add(new customSectionsObject(R.drawable.manitems2,"Man jacket",15,0));
        manImgSrc.add(new customSectionsObject(R.drawable.manitems3,"Man jacket casual",15,0));
        manImgSrc.add(new customSectionsObject(R.drawable.manitems1,"Hoodie",15,0));
       manImgSrc.add(new customSectionsObject(R.drawable.manitems2,"Man jacket",15,0));
        manImgSrc.add(new customSectionsObject(R.drawable.manitems3,"Man jacket casual",15,0));
        Man.setValue(manImgSrc);
        return Man;
    }

    public MutableLiveData<ArrayList<customSectionsObject>> getKids() {
        ArrayList<customSectionsObject> kidsImgSrc = new ArrayList<>();
        kidsImgSrc.add(new customSectionsObject(R.drawable.kidsitems1,"kids jacket",15,0));
        kidsImgSrc.add(new customSectionsObject(R.drawable.kidsitems2,"kids jacket",15,0));
        kidsImgSrc.add(new customSectionsObject(R.drawable.kidsitems3,"kids Driss",15,0));
        kidsImgSrc.add(new customSectionsObject(R.drawable.kidsitems1,"kids jacket",15,0));
        kidsImgSrc.add(new customSectionsObject(R.drawable.kidsitems2,"kids jacket",15,0));
        kidsImgSrc.add(new customSectionsObject(R.drawable.kidsitems3,"kids Driss",15,0));
        Kids.setValue(kidsImgSrc);
        return Kids;
    }

    public MutableLiveData<ArrayList<customSectionsObject>> getAll() {
        ArrayList<customSectionsObject> allImgSrc = new ArrayList<>();
        allImgSrc.add(new customSectionsObject(R.drawable.womanitems1,"woman jacket",15,0));
        allImgSrc.add(new customSectionsObject(R.drawable.womanitems2,"woman dress",15,0));
        allImgSrc.add(new customSectionsObject(R.drawable.womanitems3,"woman coat",15,0));
        allImgSrc.add(new customSectionsObject(R.drawable.womanitems1,"woman jacket",15,0));
        allImgSrc.add(new customSectionsObject(R.drawable.womanitems2,"woman dress",15,0));
        allImgSrc.add(new customSectionsObject(R.drawable.womanitems3,"woman coat",15,0));
        allImgSrc.add(new customSectionsObject(R.drawable.manitems1,"Hoodie",15,0));
        allImgSrc.add(new customSectionsObject(R.drawable.manitems2,"Man jacket",15,0));
        allImgSrc.add(new customSectionsObject(R.drawable.manitems3,"Man jacket casual",15,0));
        allImgSrc.add(new customSectionsObject(R.drawable.manitems1,"Hoodie",15,0));
        allImgSrc.add(new customSectionsObject(R.drawable.manitems2,"Man jacket",15,0));
        allImgSrc.add(new customSectionsObject(R.drawable.manitems3,"Man jacket casual",15,0));
        allImgSrc.add(new customSectionsObject(R.drawable.kidsitems1,"kids jacket",15,0));
        allImgSrc.add(new customSectionsObject(R.drawable.kidsitems2,"kids jacket",15,0));
        allImgSrc.add(new customSectionsObject(R.drawable.kidsitems3,"kids Driss",15,0));
        allImgSrc.add(new customSectionsObject(R.drawable.kidsitems1,"kids jacket",15,0));
        allImgSrc.add(new customSectionsObject(R.drawable.kidsitems2,"kids jacket",15,0));
        allImgSrc.add(new customSectionsObject(R.drawable.kidsitems3,"kids Driss",15,0));
        All.setValue(allImgSrc);
        return All;
    }
}
