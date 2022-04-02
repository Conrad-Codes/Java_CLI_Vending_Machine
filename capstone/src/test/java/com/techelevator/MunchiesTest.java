package com.techelevator;

import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;
import static org.junit.Assert.*;

public class MunchiesTest {

    private Munchies munchy;

    @Before
    public void setUp() {

        this.munchy = new Munchies("Pretzels", new BigDecimal("2.25"), "A1");

    }

    @Test
    public void test_create_munchy() {

        assertEquals("Pretzels", munchy.getName());
        assertEquals(new BigDecimal("2.25"), munchy.getPrice());
        assertEquals("A1", munchy.getLocation());

    }

    @Test
    public void test_munchy_message() {

        assertEquals("Munchy, Munchy, so Good!", munchy.getMessage());

    }


}
