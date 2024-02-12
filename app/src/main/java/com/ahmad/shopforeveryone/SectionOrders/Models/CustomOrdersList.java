package com.ahmad.shopforeveryone.SectionOrders.Models;

public class CustomOrdersList {
   String productid;
   String numProduct;
   String TotalPrice;

    public CustomOrdersList(String productid, String numProduct, String totalPrice) {
        this.productid = productid;
        this.numProduct = numProduct;
        TotalPrice = totalPrice;
    }

    public CustomOrdersList() {
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
