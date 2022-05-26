package com.app.Models;

public class Product {

    private int prodID;
    private String name;
    private String dateOfInput;
    private String price;
    private String brand;
    private boolean available;

    public Product(int prodID, String name, String price, String dateOfInput, String brand, String available) {
        this.prodID = prodID;
        this.name = name;
        this.price = price;
        this.dateOfInput = dateOfInput;
        this.brand = brand;
        if (available.compareTo("SI") == 0) {
            this.available = true;
        } else {
            this.available = false;
        }
    }

    // getters
    public int getProductID() {
        return this.prodID;
    }

    public boolean getAvailable() {
        return this.available;
    }

    public void setAvailable(boolean b) {
        this.available = b;
    }

    public String isAvailable() {

        if (available) {
            return "YES";
        } else {
            return "NO";
        }
    }

    @Override
    public String toString() {

        return prodID + " | " +
                name + " | " +
                price + " | " +
                dateOfInput + " | " +
                brand + " | " +
                isAvailable() + "\n";
    }

    public String toStringComplete() {

        return prodID + ";" +
                name + ";" +
                price + ";" +
                dateOfInput + ";" +
                brand;
    }

}
