package com.techelevator;

import com.techelevator.view.Menu;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CaTEringCapstoneCLITest {

    @Test
    public void test_dispense_change() {
        Menu menu = new Menu();
        CaTEringCapstoneCLI cli = new CaTEringCapstoneCLI(menu);
        Cashier cashier = new Cashier();

        cashier.addMoneyProvided(new BigDecimal("1.85"));

        String expectedResult = "Your change is: 1 dollar(s) 3 quarter(s) 1 dime(s) ";

        assertEquals(expectedResult, cashier.dispenseChange());
        BigDecimal zeroMoney = new BigDecimal("0.00");
        assertEquals("Your change is: ", cashier.dispenseChange());


    }

}
