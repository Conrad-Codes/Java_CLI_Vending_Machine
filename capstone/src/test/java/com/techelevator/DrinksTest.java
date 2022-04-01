package com.techelevator;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class DrinksTest {

    @Test
    public void create_Drinks_test() {
        Drinks drinks = new Drinks("Tea", new BigDecimal("2.50"), "B4");

        assertEquals("Tea", drinks.getName());
        assertEquals(new BigDecimal("2.50"), drinks.getPrice());
        assertEquals("B4", drinks.getLocation());

    }
    @Test
    public void test_inventoryCount() {
        Drinks drinks = new Drinks("Tea", new BigDecimal("2.50"), "B4");

        assertEquals(7, drinks.getInventoryCount());

        drinks.setInventoryCount();
        drinks.setInventoryCount();

        assertEquals(5, drinks.getInventoryCount());

    }

    @Test
    public void test_get_message(){
        Drinks drinks = new Drinks("Tea", new BigDecimal("2.50"), "B4");

        assertEquals("Drinky, Drinky, Slurp Slurp!", drinks.getMessage());
    }

    @Test
    public void test_isAvaliable(){
        Drinks drinks = new Drinks("Tea", new BigDecimal("2.50"), "B4");

        assertEquals(true, drinks.isAvailable());

        drinks.setAvailable(false);

        assertEquals(false, drinks.isAvailable());

    }
}
