package com.techelevator;

import java.math.BigDecimal;

public class Sandwiches extends Product {

    public Sandwiches(String name, BigDecimal price, String location) {
        super(name, price, location);
    }

    @Override
    public String getMessage() {
        return "Sandwich So Delicious, Yum!";
    }
}
