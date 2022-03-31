package com.techelevator.view;

import java.math.BigDecimal;

public abstract class Product {

    private String name;
    private BigDecimal price;
    private String location;
    private int inventoryCount = 7;

    public Product(String name, BigDecimal price, String location) {
        this.name = name;
        this.price = price;
        this.location = location;
    }

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

    public void setInventoryCount(int inventoryCount) {
        this.inventoryCount = inventoryCount;
    }
}
