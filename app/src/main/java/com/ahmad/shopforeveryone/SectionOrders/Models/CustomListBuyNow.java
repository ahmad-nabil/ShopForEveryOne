package com.ahmad.shopforeveryone.SectionOrders.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class CustomListBuyNow implements Parcelable {
     int totalpriceproduct;
int numproduct;

    public CustomListBuyNow(int numproduct, int totalpriceproduct) {
        this.totalpriceproduct = totalpriceproduct;
        this.numproduct = numproduct;
    }

    public CustomListBuyNow() {
    }

    protected CustomListBuyNow(Parcel in) {
        totalpriceproduct = in.readInt();
        numproduct = in.readInt();
    }

    public static final Creator<CustomListBuyNow> CREATOR = new Creator<CustomListBuyNow>() {
        @Override
        public CustomListBuyNow createFromParcel(Parcel in) {
            return new CustomListBuyNow(in);
        }

        @Override
        public CustomListBuyNow[] newArray(int size) {
            return new CustomListBuyNow[size];
        }
    };

    public int getTotalpriceproduct() {
        return totalpriceproduct;
    }

    public void setTotalpriceproduct(int totalpriceproduct) {
        this.totalpriceproduct = totalpriceproduct;
    }

    public int getNumproduct() {
        return numproduct;
    }

    public void setNumproduct(int numproduct) {
        this.numproduct = numproduct;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(totalpriceproduct);
        dest.writeInt(numproduct);
    }
}
