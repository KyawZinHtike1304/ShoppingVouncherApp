package com.jdc.vouncher;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class VouncherApp {

	Scanner sc = new Scanner(System.in);
	int menuId;
	int totalAmount;
	int payAmount;
	int refund;
	static ArrayList<Fruits> menuList = Fruits.createMenu();
	static ArrayList<Fruits> takenList = Fruits.createTakenList();

	public static void main(String[] args) {

		menuList.forEach(x -> System.out.println(x.toString()));

		VouncherApp customer = new VouncherApp();
		customer.choiceMenu();
	}

	public void choiceMenu() {

		menuId = showMessage("Choose Menu : ");
		if (menuId > 0 && menuId < 5 ) {
			quantityControl(showMessage("Type Quantity : "));
		} else {
			System.out.print("Invalid Menu !!  ");
			choiceMenu();
		}
	}

	public void quantityControl(int quantity) {
		var fruit = menuList.get(menuId - 1);
		var fruitName = fruit.getName();
		var fruitQty = fruit.getQuantity();

		var takenFruit = takenList.get(menuId - 1);

		if (fruitQty >= quantity) {
			takenFruit.setQuantity(takenFruit.getQuantity() + quantity);
			fruit.setQuantity(fruitQty - quantity);
			calculateBalance(quantity);
		} else {
			System.out.println(fruitName + " has only " + fruitQty + ".  Do yo want to all? (y/n) ");
			
			if (sc.next().equalsIgnoreCase("y")) {
				quantityControl(fruitQty);
			}else {
				choiceMenu();
			}
		}

	}

	public void calculateBalance(int quantity) {
		
		Fruits takenFruit = takenList.get(menuId - 1);
		totalAmount += takenFruit.getUnitPrice() * quantity;

		if (getString("Do you want another type of fruits(y/n):").equalsIgnoreCase("y")) {
			choiceMenu();
		} else {
			System.out.println("Your balance is " + totalAmount);
			System.out.println("Please enter Payment Amount by Customer ::");
			checkBalance();
		}
	}

	public void checkBalance() {

		payAmount += sc.nextInt();
		if (payAmount == totalAmount) {
			issueReceipt();
		} else if (payAmount < totalAmount) {
			System.out.println("Your Balance is not enough !! Need pay more " + (totalAmount - payAmount));
			checkBalance();
		} else {
			refund = payAmount - totalAmount;
			System.out.println("Take Refunds : " + refund);
			issueReceipt();
		}
	}

	public void issueReceipt() {
		Iterator<Fruits> itr = takenList.iterator();

		System.out.println("==================================================");
		System.out.println("                   R E C E I P T                  ");
		System.out.println("==================================================");

		System.out.println("ITEM\t\t\t  PRICE\t    QTY\t\t AMOUNT");

		while (itr.hasNext()) {
			var fruit = itr.next();
			if (fruit.getQuantity() != 0) {
				System.out.printf("%-10s   \t\t%d \t%2d   %10d %n", fruit.getName(),fruit.getUnitPrice(),
						fruit.getQuantity(), (fruit.getQuantity() * fruit.getUnitPrice()));
			}
		}
		System.out.println("==================================================");
		System.out.printf("TOTAL AMOUNT  \t\t\t\t\t\t %d%n",totalAmount);
		System.out.printf("CASH          \t\t\t\t\t\t %d%n",payAmount);
		System.out.printf("CHANGE        \t\t\t\t\t\t %5d%n",refund);
		System.out.println("==================================================");
		System.out.println("                   THANK YOU                      ");
		System.out.println("==================================================");
	}

	public int showMessage(String message) {
		System.out.println(message);
		return sc.nextInt();
	}

	String getString(String message) {
		System.out.print(message);
		return sc.next();
	}

}