package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Menu {

    public void mainMenu() {
        System.out.println("(D) Display caTEring Items\n(P) Purchase\n(E) Exit");
    }

    public void purchaseMenu() {
        System.out.println("(M) Feed Money\n(S) Select Item\n(F) Finish Transaction");
    }

}
