package com.techu.apitechu.models;

public class Product {
    public String id;
    private String description;
    private float price;

    public Product() {
    }

    public Product(String id, String description, float price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }
}
