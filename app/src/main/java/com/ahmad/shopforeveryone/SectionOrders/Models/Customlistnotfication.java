package com.ahmad.shopforeveryone.SectionOrders.Models;

public class Customlistnotfication {
    String productid;
    String numProduct;
    String TotalPrice;
String typepayment;

    public Customlistnotfication(String productid, String numProduct, String totalPrice, String typepayment) {
        this.productid = productid;
        this.numProduct = numProduct;
        TotalPrice = totalPrice;
        this.typepayment = typepayment;
    }

    public String getTypepayment() {
        return typepayment;
    }

    public void setTypepayment(String typepayment) {
        this.typepayment = typepayment;
    }

    public Customlistnotfication() {
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getNumProduct() {
        return numProduct;
    }

    public void setNumProduct(String numProduct) {
        this.numProduct = numProduct;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        TotalPrice = totalPrice;
    }

}
