package model;

import java.util.ArrayList;

public class Trader {
	private int id;
	private String name;
	private boolean isSeller, isBuyer;
	private ArrayList<Electronic> electronics = new ArrayList<Electronic>();
	private int phoneNumber;
	private String address;
	
	public Trader(int id, String name, boolean isSeller, boolean isBuyer, int phoneNumber, String address) {
		this.id = id;
		this.name = name;
		this.isSeller = isSeller;
		this.isBuyer = isBuyer;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isSeller() {
		return isSeller;
	}
	
	public void setSeller() {
		this.isSeller = true;
		this.isBuyer = false;
	}
	
	public boolean isBuyer() {
		return isBuyer;
	}
	
	public void setBuyer() {
		this.isBuyer = true;
		this.isSeller = false;
	}
	
	public ArrayList<Electronic> getElectronics() {
		return electronics;
	}
	
	public void setElectronics(ArrayList<Electronic> electronics) {
		this.electronics = electronics;
	}
	
	public int getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void addElectronic(Electronic electronic) {
		electronics.add(electronic);
	}
	
	public void modifyProperties(Electronic electronic) {
		for (int i = 0; i < electronics.size(); i++) {
			if (electronics.get(i).getId() == electronic.getId()) {
				electronics.set(i, electronic);
				break;
			}
		}
	}
}
