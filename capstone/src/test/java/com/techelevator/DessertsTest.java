package com.techelevator;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class DessertsTest {

    @Test
    public void create_Desserts_test(){
        Desserts dessert = new Desserts("Cannoli", new BigDecimal("2.50"), "B4");

        assertEquals("Cannoli", dessert.getName());
        assertEquals(new BigDecimal("2.50"), dessert.getPrice());
        assertEquals("B4", dessert.getLocation());
    }
}
