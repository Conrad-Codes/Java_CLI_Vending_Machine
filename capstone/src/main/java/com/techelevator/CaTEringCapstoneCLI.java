package com.techelevator;

import com.techelevator.view.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class CaTEringCapstoneCLI {



	private Menu menu;
	private Scanner inputScanner;
	private String displayOfferings = "";
	private List<Product> offerings = new ArrayList<>();




	public CaTEringCapstoneCLI(Menu menu) {
		this.menu = menu;
		this.inputScanner = new Scanner(System.in);
	}

	public static void main(String[] args) {
		Menu menu = new Menu();
		CaTEringCapstoneCLI cli = new CaTEringCapstoneCLI(menu);


		cli.run();
	}

	public void run() {

		File inputFile = new File("catering.csv");



		try {
			Scanner fileScanner = new Scanner(inputFile);


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

		boolean keepRunning = true;

		do {
			menu.mainMenu();
			String menuChoice = inputScanner.nextLine();
			menuChoice = menuChoice.toUpperCase();

			if (menuChoice.equals("D")) {
				System.out.println(offeringsDisplay());
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
			menuChoice = menuChoice.toUpperCase();
			if (menuChoice.equals("M")) {
				// money!
			} else if (menuChoice.equals("S")) {
				//display offerings
				System.out.println(offeringsDisplay());
				//Ask for and take in location of offerings
				System.out.println("Please enter location of desired item");
				String itemLocation = inputScanner.nextLine().toUpperCase();
				//check if actual location exists
				boolean locationFound = false;
				for (Product offering : offerings){
					if (offering.getLocation().equals(itemLocation)){
						locationFound = true;
						break;
					}
				}
				if (!locationFound) {
					System.out.println("Location not found");
				}
				// check if offering available
				//yes - dispense
				//no - NLA message
				//print dispense message
				//update inventory, return to purchase menu
			} else if (menuChoice.equals("F")) {
				keepRunning = false;
			}
		} while (keepRunning);
	}

	public String offeringsDisplay(){
		String str = "";
		for (Product offering : offerings){
			str += offering.getLocation() + " " + offering.getName() + " " + offering.getPrice();
			if (!offering.isAvailable()){
				str+= " NO LONGER AVAILABLE\n";
			}else {
				str += "\n";
			}
		}
		return str;
	}
}
