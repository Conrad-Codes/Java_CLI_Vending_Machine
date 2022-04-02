package com.techelevator;

import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;
import static org.junit.Assert.*;

public class SandwichesTest {

    private Sandwiches sandwich;

    @Before
    public void setUp() {

        this.sandwich = new Sandwiches("PB and J", new BigDecimal("3.50"), "B2");

    }

    @Test
    public void test_create_sandwich() {

        assertEquals("PB and J", sandwich.getName());
        assertEquals(new BigDecimal("3.50"), sandwich.getPrice());
        assertEquals("B2", sandwich.getLocation());

    }

    @Test
    public void test_sandwich_message() {

        assertEquals("Sandwich So Delicious, Yum!", sandwich.getMessage());

    }

}
