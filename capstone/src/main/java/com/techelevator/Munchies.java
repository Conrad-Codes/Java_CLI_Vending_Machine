package com.techelevator;

import java.math.BigDecimal;

public class Munchies extends Product {

    public Munchies(String name, BigDecimal price, String location) {
        super(name, price, location);
    }

    @Override
    public String getMessage() {
        return "Munchy, Munchy, so Good!";
    }
}
