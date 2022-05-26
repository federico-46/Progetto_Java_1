package com.app.Models;

public class Sale {

    private int saleID;
    private User user;
    private Product product;

    public Sale(int saleID, User user, Product product) {
        this.saleID = saleID;
        this.user = user;
        this.product = product;
    }

    // getters
    public int getSaleID() {
        return this.saleID;
    }

    public User getUser() {
        return this.user;
    }

    public Product getProduct() {
        return this.product;
    }

    public Product get(int parseInt) {
        return null;
    }
}
