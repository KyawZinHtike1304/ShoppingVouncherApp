package com.jdc.vouncher;

import java.util.ArrayList;

public class Fruits {

	private int id;
	private String name;
	private int quantity;
	private int unitPrice;

	public Fruits(int id, String name, int quantity, int unitPrice) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}
	
	
	
	public static ArrayList<Fruits> createMenu() {
		ArrayList<Fruits> fruits = new ArrayList<Fruits>();
		fruits.add(new Fruits(1, "Apple", 50, 1500));
		fruits.add(new Fruits(2, "Banana", 50, 1000));
		fruits.add(new Fruits(3, "Orange", 50, 800));
		fruits.add(new Fruits(4, "Lemon", 50, 300));

		return fruits;
	}
	
	public static ArrayList<Fruits> createTakenList() {
		ArrayList<Fruits> fruits = new ArrayList<Fruits>();
		fruits.add(new Fruits(1, "Apple", 0, 1500));
		fruits.add(new Fruits(2, "Banana", 0, 1000));
		fruits.add(new Fruits(3, "Orange", 0, 800));
		fruits.add(new Fruits(4, "Lemon", 0, 300));

		return fruits;
	}
	
	
	public String getName() {
		return name;
	}
	
	public int getUnitPrice() {
		return unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "%s. %s	%d	%d".formatted(id, name, quantity, unitPrice);
	}

}
