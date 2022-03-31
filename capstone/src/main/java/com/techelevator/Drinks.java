package com.techelevator;

import java.math.BigDecimal;

public class Drinks extends Product {

    public Drinks(String name, BigDecimal price, String location) {
        super(name, price, location);
    }

    @Override
    public String getMessage() {
        return "Drinky, Drinky, Slurp Slurp!";
    }
}
