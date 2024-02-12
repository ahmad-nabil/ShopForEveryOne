package com.ahmad.shopforeveryone.SectionOrders.Models;

public class customSectionsObject {
    int imgSrc;
    String name;
    int price;
    int count;

    public customSectionsObject(int imgSrc, String name, int price, int count) {
        this.imgSrc = imgSrc;
        this.name = name;
        this.price = price;
        this.count = count;
    }

    public int getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(int imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
