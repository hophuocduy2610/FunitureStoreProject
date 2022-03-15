package com.example.thlistview.model;

public class Product {
    int id;
    String name;
    String shopName;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Product(int id, String name, String shopName) {
        this.id = id;
        this.name = name;
        this.shopName = shopName;
    }
}
