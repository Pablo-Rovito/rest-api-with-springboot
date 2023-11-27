package com.techu.apitechu.models;

public class ProductModel extends GeneralResponse{
    public String id;
    private String description;
    private Float price;

    public ProductModel() {
    }

    public ProductModel(String id, String description, Float price, String message) {
        super(message);
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

    public Float getPrice() {
        return price;
    }
}
