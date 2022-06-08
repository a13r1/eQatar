package model;

public abstract class Electronic {
	private int id;
	private double price;
	private String brand, color;
	private boolean isSold;
	public String details;
	
	public Electronic(int id, double price, String brand, String color) {
		this.id = id;
		this.price = price;
		this.brand = brand;
		this.color = color;
		isSold = false;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public boolean isSold() {
		return isSold;
	}
	
	public void setSold(boolean isSold) {
		this.isSold = isSold;
	}
	
	public abstract void createDetails();

	public String getDetails() {
		return details;
	}
}
