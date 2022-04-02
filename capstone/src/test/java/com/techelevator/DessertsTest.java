package com.techelevator;

import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;
import static org.junit.Assert.*;

public class DessertsTest {

    private Desserts dessert;

    @Before
    public void setUp() {

        this.dessert = new Desserts("Skuttles", new BigDecimal("1.50"), "C3");

    }

    @Test
    public void test_create_dessert() {

        assertEquals("Skuttles", dessert.getName());
        assertEquals(new BigDecimal("1.50"), dessert.getPrice());
        assertEquals("C3", dessert.getLocation());

    }

    @Test
    public void test_dessert_message() {

        assertEquals("Sugar, Sugar, so Sweet!", dessert.getMessage());

    }

}
