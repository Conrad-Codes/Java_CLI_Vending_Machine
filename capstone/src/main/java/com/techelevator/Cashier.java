package com.techelevator;

import java.math.BigDecimal;

public class Cashier {

    private BigDecimal moneyProvided = new BigDecimal("0.00");

    public Cashier() {
    }

    public BigDecimal getMoneyProvided() {
        return moneyProvided;
    }

    public void addMoneyProvided(BigDecimal moneyProvided) {
        this.moneyProvided = this.moneyProvided.add(moneyProvided);
    }

    public void subtractMoney(BigDecimal price) {
        this.moneyProvided = this.moneyProvided.subtract(price);
    }

    public String dispenseChange(){
        BigDecimal dollars = new BigDecimal(0);
        BigDecimal quarters = new BigDecimal(0);
        BigDecimal dimes = new BigDecimal(0);
        BigDecimal nickels = new BigDecimal(0);

        BigDecimal[] holder = this.moneyProvided.divideAndRemainder(new BigDecimal("1.00"));
        dollars = holder[0];
        BigDecimal moneyLeft = holder[1];

        if (moneyLeft.compareTo(new BigDecimal("0.00")) == 1){
            holder = moneyLeft.divideAndRemainder(new BigDecimal("0.25"));
            quarters = holder[0];
            moneyLeft = holder[1];
        }
        if (moneyLeft.compareTo(new BigDecimal("0.00")) == 1){
            holder = moneyLeft.divideAndRemainder(new BigDecimal("0.10"));
            dimes = holder[0];
            moneyLeft = holder[1];
        }if (moneyLeft.compareTo(new BigDecimal("0.00")) == 1) {
            holder = moneyLeft.divideAndRemainder(new BigDecimal("0.05"));
            nickels = holder[0];
            moneyLeft = holder[1];
        }
        BigDecimal zero = new BigDecimal("0.00");
        String changeOutStr = "Your change is: ";
        if (dollars.compareTo(zero) == 1){
            changeOutStr += dollars + " dollar(s) ";
        }
        if (quarters.compareTo(zero) == 1){
            changeOutStr += quarters + " quarter(s) ";
        }
        if (dimes.compareTo(zero) == 1){
            changeOutStr += dimes + " dime(s) ";
        }
        if (nickels.compareTo(zero) == 1){
            changeOutStr += nickels + " nickel(s) ";
        }

        this.moneyProvided = new BigDecimal("0.00");
        return (changeOutStr + "\n");
    }

}
