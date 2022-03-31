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

	public CaTEringCapstoneCLI(Menu menu) {
		this.menu = menu;
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
		while (true) {
			//  to do -- build out main menu

		}
	}
}
