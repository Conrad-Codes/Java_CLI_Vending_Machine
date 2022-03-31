package com.techelevator;

import com.techelevator.view.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CaTEringCapstoneCLI {



	private Menu menu;
	private Scanner inputScanner;

	public CaTEringCapstoneCLI(Menu menu) {
		this.menu = menu;
		this.inputScanner = new Scanner(System.in);
	}

	public static void main(String[] args) {
		Menu menu = new Menu();
		CaTEringCapstoneCLI cli = new CaTEringCapstoneCLI(menu);
		File inputFile = new File("catering.csv");

		try {
			Scanner fileScanner = new Scanner(inputFile);
			List<Product> offerings = new ArrayList<>();


			while (fileScanner.hasNextLine()){
				String line = fileScanner.nextLine();
				String[] lineArr = line.split("\\,");

				if (lineArr[2].equals("Munchy")) {
					offerings.add(new Munchies(lineArr[1],new BigDecimal(lineArr[3]), lineArr[0]));
				}else if (lineArr[2].equals("Dessert")) {
					offerings.add(new Desserts(lineArr[1],new BigDecimal(lineArr[3]), lineArr[0]));
				}else if (lineArr[2].equals("Sandwich")) {
					offerings.add(new Sandwiches(lineArr[1],new BigDecimal(lineArr[3]), lineArr[0]));
				}else if (lineArr[2].equals("Drink")) {
					offerings.add(new Drinks(lineArr[1],new BigDecimal(lineArr[3]), lineArr[0]));
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}

//		cli.run();
	}

	public void run() {

		boolean keepRunning = true;

		do {
			menu.mainMenu();
			String menuChoice = inputScanner.nextLine();
			if (menuChoice.equals("D")) {
				// call offerings
			} else if (menuChoice.equals("P")) {
				runPurchase();
			} else if (menuChoice.equals("E")) {
				keepRunning = false;
			}

		} while (keepRunning);

	}

	public void runPurchase() {

		boolean keepRunning = true;

		do {
			menu.purchaseMenu();
			String menuChoice = inputScanner.nextLine();
			if (menuChoice.equals("M")) {
				// money!
			} else if (menuChoice.equals("S")) {
				// select offering
			} else if (menuChoice.equals("F")) {
				keepRunning = false;
			}

		} while (keepRunning);

	}

}
