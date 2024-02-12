package com.ahmad.shopforeveryone.SectionOrders.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class CustomItemClass implements Parcelable {
    String itemname;
    int count;
    int price;
    int totalprice;
    int imgsrc;

    public CustomItemClass() {
    }

    public CustomItemClass(String itemname, int count, int price, int totalprice, int imgsrc) {
        this.itemname = itemname;
        this.count = count;
        this.price = price;
        this.totalprice = totalprice;
        this.imgsrc = imgsrc;
    }

    protected CustomItemClass(Parcel in) {
        itemname = in.readString();
        count = in.readInt();
        price = in.readInt();
        totalprice = in.readInt();
        imgsrc = in.readInt();
    }

    public static final Creator<CustomItemClass> CREATOR = new Creator<CustomItemClass>() {
        @Override
        public CustomItemClass createFromParcel(Parcel in) {
            return new CustomItemClass(in);
        }

        @Override
        public CustomItemClass[] newArray(int size) {
            return new CustomItemClass[size];
        }
    };

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public int getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(int imgsrc1) {
        this.imgsrc = imgsrc1;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(itemname);
        dest.writeInt(count);
        dest.writeInt(price);
        dest.writeInt(totalprice);
        dest.writeInt(imgsrc);
    }
}
