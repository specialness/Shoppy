package com.example.darks.shopper;


import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Product {
    @SerializedName("ID")
    private int ID;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("owner")
    private String owner;
    @SerializedName("imagePath")
    private String imagePath;
    @SerializedName("dateAdded")
    private Date dateAdded;
    @SerializedName("quantity")
    private int quantity;
    @SerializedName("price")
    private double price;

    public Product(int ID, String title, String description, String owner, String imagePath, Date dateAdded, int quantity, double price) {
        this.ID = ID;
        this.title = title;
        this.description = description;
        this.owner = owner;
        this.imagePath = imagePath;
        this.dateAdded = dateAdded;
        this.quantity = quantity;
        this.price = price;
    }

    public int getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getOwner() {
        return owner;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}
