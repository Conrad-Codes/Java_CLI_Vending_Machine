package com.techelevator;

import com.techelevator.view.Menu;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.*;

public class CaTEringCapstoneCLI {



	private Menu menu;
	private Scanner inputScanner;
	private String displayOfferings = "";
	private List<Product> offerings = new ArrayList<>();
	private BigDecimal moneyProvided = new BigDecimal("0.00");
//	private File auditFile = new File("Audit.txt");


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


//		try {
//			auditFile.createNewFile();
//		} catch (IOException e) {
//			System.out.println("File IO exception");
//		}



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
			System.out.println("\n Current Money Provided: $" + moneyProvided);

			String menuChoice = inputScanner.nextLine();
			menuChoice = menuChoice.toUpperCase();

			if (menuChoice.equals("M")) {
				// money!
				boolean moneyDone = false;
				do {
					System.out.println("Please Feed Money or press X when done");
					System.out.println("Current Money Provided: $" + moneyProvided);
					String inputMoney = inputScanner.nextLine().toUpperCase();
					if (inputMoney.equals("X")) {
						moneyDone= true;
					}else {
						BigDecimal moneyAdded = new BigDecimal(inputMoney);
						moneyProvided = moneyProvided.add(moneyAdded);
//						try (PrintWriter log = new PrintWriter(new FileOutputStream(auditFile, true))) {
//							log.append(logDateTime() + "MONEY FED:		" + "$" + moneyAdded + ".00" + " $" + moneyProvided);
//						} catch (FileNotFoundException e) {
//							System.out.println("File not found");
//						}
					}

				}while (!moneyDone);

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
						//check if item is available
						if (offering.isAvailable()) {
							//check to see if enough money was provided
							if (moneyProvided.compareTo(offering.getPrice()) != -1){

								BigDecimal moneyBeforeDispense = moneyProvided;
								moneyProvided = moneyProvided.subtract(offering.getPrice());
//								try (PrintWriter log = new PrintWriter(new FileOutputStream(auditFile, true))) {
//									log.append(logDateTime() + offering.getName() + " " + offering.getLocation() + "	$" + moneyBeforeDispense + " $" + moneyProvided);
//								} catch (FileNotFoundException e) {
//									System.out.println("File not found");
//								}

								System.out.println(offering.getName() + " $" + offering.getPrice() + " $" + moneyProvided);
								System.out.println(offering.getMessage());
								offering.setInventoryCount();

								if (offering.getInventoryCount() < 1) {
									offering.setAvailable(false);
									break;
								}
							}else {
								System.out.println("Not enough funds for this item \n");
							}
						} else {
							System.out.println("Item not available");
							break;
						}
					}
				}
				if (!locationFound) {
					System.out.println("Location not found");
				}

			} else if (menuChoice.equals("F")) {
				System.out.println(dispenseChange(moneyProvided));
				BigDecimal changeProvided = moneyProvided;
				moneyProvided = new BigDecimal("0.00");
//				PrintWriter log = null;
//				try {
//					log = new PrintWriter(new FileOutputStream(auditFile, true));
//					log.println("\n");
//					log.println(logDateTime() + "CHANGE GIVEN:		" + "$" + changeProvided + " $" + moneyProvided + "\n *****");
//					log.flush();
//					log.close();
//
//				} catch (FileNotFoundException e) {
//					e.printStackTrace();
//				}


				keepRunning = false;
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

//	public static String logDateTime() {
//		LocalDateTime logDate = LocalDateTime.now();
//		String amPM = "";
//		int month = logDate.getMonthValue();
//		int date = logDate.getDayOfMonth();
//		int year = logDate.getYear();
//		int hour = logDate.getHour();
//		if (hour>=12) {
//			amPM = "PM";
//			if (hour >=13) {
//				hour-=12;
//			}
//		}else {
//			amPM = "AM";
//		}
//		int minute = logDate.getMinute();
//		int second = logDate.getSecond();
//		String dateTimeString = month + "/" + date + "/" + year + " " + hour + ":" + minute + ":" + second + " " + amPM + " ";
//		return dateTimeString;
//	}

	public String dispenseChange(BigDecimal moneyProvided){
		BigDecimal dollars = new BigDecimal(0);
		BigDecimal quarters = new BigDecimal(0);
		BigDecimal dimes = new BigDecimal(0);
		BigDecimal nickels = new BigDecimal(0);

		BigDecimal[] holder = moneyProvided.divideAndRemainder(new BigDecimal("1.00"));
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

		return (changeOutStr);
	}
}
