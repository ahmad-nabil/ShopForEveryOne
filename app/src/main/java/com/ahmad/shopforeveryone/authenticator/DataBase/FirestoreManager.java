package com.ahmad.shopforeveryone.authenticator.DataBase;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FirestoreManager {
    private final FirebaseFirestore fireStore;

    public FirestoreManager() {
        fireStore=FirebaseFirestore.getInstance();
    }
    //add user
    public Task <Void>AddUser(String collection, String UID,DataUser dataUser){
        return fireStore.collection(collection).document(UID).set(dataUser);
    }

    public Task <Void>updateUser(String collection,String documentId ,HashMap <String,Object> dataUser){
        return fireStore.collection(collection).document(documentId).update(dataUser);    }
    public Task<Void> updatePassword(String userId, String newPassword) {

        Map<String, Object> updates = new HashMap<>();
        updates.put("password", newPassword);
        return fireStore.collection("users").document(userId).update(updates);

    }
    public Task <DocumentSnapshot>getUser(String UID){
        return fireStore.collection("users").document(UID).get();
    }
}
