package com.ahmad.shopforeveryone.authenticator.DataBase;

import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.HashMap;

public class firebaseauthmanager {
    private final FirebaseAuth auth;
FirestoreManager firestoreManager;
//initilize firebase authencation
    public firebaseauthmanager() {
        auth=FirebaseAuth.getInstance();
        firestoreManager=new FirestoreManager();
    }
    //register method
    public Task <AuthResult> Register(DataUser dataUser){
return auth.createUserWithEmailAndPassword(dataUser.Email,dataUser.password);
    }
    public Task <AuthResult> SignIn(String email,String password){
        return auth.signInWithEmailAndPassword(email,password);
    }
    public void SignOut() {
        auth.signOut();
    }
    public Task <Void>  SendCodeResetEmail(String email) {
  return  auth.sendPasswordResetEmail(email);
    }
    public Task <String>  VerifiedCodeResetEmail(String code) {
        return  auth.verifyPasswordResetCode(code);
    }
    public Task <Void>  UpdatePassword(String code,String password) {
        return  auth.confirmPasswordReset(code,password);
    }
    public Task <Void>updateEmail(String email){
        return auth.getCurrentUser().updateEmail(email);
    }
    public Task <Void>updatepassword(String password){
        return auth.getCurrentUser().updatePassword(password);
    }
    public FirebaseUser getCurrentUser() {

        return auth.getCurrentUser();
    }
public void  RegisterUserData(DataUser dataUser){
        firestoreManager.AddUser("users", auth.getUid(),dataUser);
}
    public void  updatepasswordInFireStore(String password,String UserId){
        firestoreManager.updatePassword(UserId, password);
    }
    //get user data for mvvm profile data
    public void getuserData(String UserId, MutableLiveData<DataUser> userData){
        firestoreManager.getUser(UserId).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot documentSnapshot = task.getResult();
                if (documentSnapshot != null && documentSnapshot.exists()) {
                    DataUser Data = documentSnapshot.toObject(DataUser.class);
                    userData.setValue(Data);
                }
            }


        });

    }

    public void UpdateUserData(String UserId, HashMap<String,Object>List){
        firestoreManager.updateUser("users",auth.getCurrentUser().getUid(),List);

    }

}
