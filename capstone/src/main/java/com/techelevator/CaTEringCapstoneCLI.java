package com.techelevator;

import com.techelevator.view.Menu;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

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

		File inputFile = new File("catering1.csv");

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
			String menuChoice = inputScanner.nextLine().toUpperCase();
			System.out.println("");

			if (menuChoice.equals("D")) {
				System.out.println(offeringsDisplay());
			} else if (menuChoice.equals("P")) {
				runPurchase();
			} else if (menuChoice.equals("E")) {
				keepRunning = false;
			} else {
				System.out.println("Invalid input\n");
			}
		} while (keepRunning);

	}

	public void runPurchase() {
		LogWriter pw = new LogWriter();
		Cashier cashier = new Cashier();

		boolean keepRunning = true;

		do {
			menu.purchaseMenu();
			System.out.println("\n Current Money Provided: $" + cashier.getMoneyProvided());

			String menuChoice = inputScanner.nextLine();
			menuChoice = menuChoice.toUpperCase();
			System.out.println();

			if (menuChoice.equals("M")) {
				// money!
				boolean moneyDone = false;
				do {
					System.out.println("Please Feed Money (1, 5, 10, or 20) or press X when done");
					System.out.println("Current Money Provided: $" + cashier.getMoneyProvided());
					String inputMoney = inputScanner.nextLine().toUpperCase();
					if (inputMoney.equals("X")) {
						moneyDone= true;
						System.out.println();
					}else  if(inputMoney.equals("1") || inputMoney.equals("5") || inputMoney.equals("10") || inputMoney.equals("20")){
						try {
							BigDecimal moneyAdded = new BigDecimal(inputMoney);
							cashier.addMoneyProvided(moneyAdded);
							pw.appendMoney(moneyAdded, cashier.getMoneyProvided());
							System.out.println();
						} catch (NumberFormatException e) {
							System.out.println("Invalid input\n");
						}
					} else {
						System.out.println("\nInvalid input\n");
					}

				}while (!moneyDone);

			} else if (menuChoice.equals("S")) {
				//display offerings
				System.out.println(offeringsDisplay());
				System.out.println("Current Money Provided: $" + cashier.getMoneyProvided());
				//Ask for and take in location of offerings
				System.out.println("Please enter location of desired item");
				String itemLocation = inputScanner.nextLine().toUpperCase();
				System.out.println();
				//check if actual location exists
				boolean locationFound = false;
				for (Product offering : offerings){
					if (offering.getLocation().equals(itemLocation)){
						locationFound = true;
						//check if item is available
						if (offering.isAvailable()) {
							//check to see if enough money was provided
							if (cashier.getMoneyProvided().compareTo(offering.getPrice()) != -1){
								BigDecimal moneyBeforeDispense = cashier.getMoneyProvided();
								cashier.subtractMoney(offering.getPrice());
								pw.appendItemDispense(offering.getName(), offering.getLocation(), moneyBeforeDispense, cashier.getMoneyProvided());

								System.out.println(offering.getName() + " $" + offering.getPrice() + " \nMoney left: $" + cashier.getMoneyProvided());
								System.out.println(offering.getMessage() + "\n");
								offering.setInventoryCount();

								if (offering.getInventoryCount() < 1) {
									offering.setAvailable(false);
									break;
								}
							}else {
								System.out.println("Not enough funds for this item\n");
							}
						} else {
							System.out.println("Item not available\n");
							break;
						}
					}
				}
				if (!locationFound) {
					System.out.println("Location not found\n");
				}

			} else if (menuChoice.equals("F")) {
				BigDecimal changeProvided = cashier.getMoneyProvided();
				System.out.println(cashier.dispenseChange());
				pw.appendFinishTransaction(changeProvided, cashier.getMoneyProvided());
				keepRunning = false;
			} else {
				System.out.println("Invalid Input\n");
			}
		} while (keepRunning);
	}

	public String offeringsDisplay(){
		String str = "";
		for (Product offering : offerings){
			str += offering.getLocation() + " " + offering.getName() + " $" + offering.getPrice() + " QTY " + offering.getInventoryCount();
			if (!offering.isAvailable()){
				str+= " NO LONGER AVAILABLE\n";
			}else {
				str += "\n";
			}
		}
		return str;
	}
}
