package com.ahmad.shopforeveryone.authenticator.DataBase;

import androidx.annotation.NonNull;

import com.ahmad.shopforeveryone.SectionOrders.Models.CustomItemClass;
import com.ahmad.shopforeveryone.SectionOrders.Models.CustomOrdersList;
import com.ahmad.shopforeveryone.notfication.NotficationCustomClass;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.function.Consumer;

public class RealTimeDataBaseManager {
FirebaseDatabase firebaseDatabase;
DatabaseReference databaseReference;

String UID;
firebaseauthmanager authmanager;
String productsid;
    public RealTimeDataBaseManager() {
        firebaseDatabase=FirebaseDatabase.getInstance();
        authmanager=new firebaseauthmanager();
        if (authmanager.getCurrentUser() != null) {
            UID= authmanager.getCurrentUser().getUid();
        }
    }
    public Task <Void>AddTocart(CustomItemClass itemClass) {
        databaseReference = firebaseDatabase.getReference().child("Carts").child(UID);
        return databaseReference.child(itemClass.getItemname()).setValue(itemClass);
    }
    public Task <Void>AddToNotfiList(NotficationCustomClass notfiClass) {
     if (UID!=null){
         databaseReference = firebaseDatabase.getReference().child("Notfications").child(UID);
         return databaseReference.child(notfiClass.getTitle()).setValue(notfiClass);
     }else {
         return null;
     }

    }
        public Task <Void>AddToOrdersList(CustomOrdersList ordersList){
            databaseReference = firebaseDatabase.getReference().child("Orders").child(UID).child(productsid);
            return databaseReference.setValue(ordersList);
        }


public void getCartList(final Consumer<ArrayList<CustomItemClass>>successValue,final Consumer<Boolean>failedValue){
    databaseReference = firebaseDatabase.getReference().child("Carts").child(UID);
databaseReference.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        ArrayList<CustomItemClass> cartItems = new ArrayList<>();
        if (snapshot.exists()){
            for (DataSnapshot s :snapshot.getChildren()) {
                CustomItemClass itemClass=s.getValue(CustomItemClass.class);
                if (itemClass!=null){
                    cartItems.add(itemClass);
                }
            }
            successValue.accept(cartItems);
            failedValue.accept(false);}else {

            failedValue.accept(true);
        }

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});

}
public void getOrderList(final Consumer <ArrayList<CustomOrdersList>>orderValue, final Consumer <Boolean>failedValue){
    databaseReference = firebaseDatabase.getReference().child("Orders").child(UID);
databaseReference.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
    ArrayList <CustomOrdersList>List=new ArrayList<>();

       if(snapshot.exists()){

for(DataSnapshot s:snapshot.getChildren()){
    CustomOrdersList list=s.getValue(CustomOrdersList.class);
    if(list!=null){
        List.add(list);

    }
}
orderValue.accept(List);

           failedValue.accept(false);

       }else {
           failedValue.accept(true);

       }

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});
}
public void getnotfiList(final Consumer<ArrayList<NotficationCustomClass>>list,final Consumer<Boolean>failedValue){
      firebaseDatabase.getReference().child("Notfications").child(UID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    ArrayList <NotficationCustomClass>list_notfi=new ArrayList<>();
                    for (DataSnapshot s :snapshot.getChildren()) {
                        NotficationCustomClass customClass=s.getValue(NotficationCustomClass.class);
                        list_notfi.add(customClass);
                    }
                    list.accept(list_notfi);
                    failedValue.accept(false);
                }else {
                    failedValue.accept(true);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
}
public Task<Void>RemoveItemFromCart(String itemClass){
        databaseReference = firebaseDatabase.getReference().child("Carts").child(UID).child(itemClass);

        return databaseReference.removeValue();
    }
    public void RemoveCart(){
    firebaseDatabase.getReference().child("Carts").child(UID).removeValue();

    }
    public void RemoveOrder(String Pid){
        firebaseDatabase.getReference().child("Orders").child(UID).child(Pid).removeValue();

    }
    public void Removenotfi(){
        firebaseDatabase.getReference().child("Notfications").child(UID).removeValue();

    }
    public String generateOrderId() {
        DatabaseReference ordersReference = firebaseDatabase.getReference().child("OrdersID");
        DatabaseReference newOrderReference = ordersReference.push();
        productsid=newOrderReference.getKey();
        ordersReference.child(newOrderReference.getKey()).setValue(UID);
        return newOrderReference.getKey();
    }
}
