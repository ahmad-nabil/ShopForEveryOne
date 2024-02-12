package com.ahmad.shopforeveryone.authenticator;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahmad.shopforeveryone.authenticator.DataBase.DataUser;
import com.ahmad.shopforeveryone.authenticator.DataBase.firebaseauthmanager;

public class Profilemodel extends ViewModel {
    private MutableLiveData<DataUser> listData = new MutableLiveData<>();
    private MutableLiveData<Boolean> dataFetched = new MutableLiveData<>();
    firebaseauthmanager authManager = new firebaseauthmanager();

    public void fetchUserData() {
        String UserId = authManager.getCurrentUser().getUid();
        authManager.getuserData(UserId, listData);
    }

    public MutableLiveData<DataUser> getUserData() {
        if (listData.getValue() == null) {
            fetchUserData();
        }
        return listData;
    }


}
