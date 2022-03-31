package com.techelevator;

import java.math.BigDecimal;

public abstract class Product {

    private String name;
    private BigDecimal price;
    private String location;
    private int inventoryCount = 7;
    private boolean isAvailable;


    public Product(String name, BigDecimal price, String location) {
        this.name = name;
        this.price = price;
        this.location = location;
        this.isAvailable = true;
    }

    public abstract String getMessage();

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getLocation() {
        return location;
    }

    public int getInventoryCount() {
        return inventoryCount;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setInventoryCount() {
        this.inventoryCount--;
    }
}
