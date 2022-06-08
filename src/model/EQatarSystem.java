package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class EQatarSystem {
	private ArrayList<Electronic> electronics = new ArrayList<Electronic>();
	private ArrayList<Trader> traders = new ArrayList<Trader>();
	private ArrayList<Deal> deals = new ArrayList<Deal>();
	
	public ArrayList<Electronic> getElectronics() {
		return electronics;
	}
	
	public void setElectronics(ArrayList<Electronic> electronics) {
		this.electronics = electronics;
	}
	
	public ArrayList<Trader> getTraders() {
		return traders;
	}
	
	public void setTraders(ArrayList<Trader> traders) {
		this.traders = traders;
	}

	public ArrayList<Deal> getDeals() {
		return deals;
	}

	public void setDeals(ArrayList<Deal> deals) {
		this.deals = deals;
	}
	
	public void addElectronic(Electronic electronic) {
		electronics.add(electronic);
	}
	
	public Electronic findElectronic(int id) {
		for (int i = 0; i < electronics.size(); i++) {
			if (electronics.get(i).getId() == id) {
				return electronics.get(i);
			}
		}
		return null;
	}
	
	public void changeElectronicStatusToSold(int id) {
		for (int i = 0; i < electronics.size(); i++) {
			if (electronics.get(i).getId() == id) {
				electronics.get(i).setSold(true);
				break;
			}
		}
	}
	
	public void addTrader(Trader trader) {
		traders.add(trader);
	}
	
	public Trader findTrader(int id) {
		for (int i = 0; i < traders.size(); i++) {
			if (traders.get(i).getId() == id) {
				return traders.get(i);
			}
		}
		return null;
	}
	
	public ArrayList<Smartphone> getListOfAvailableSmartphones() {
		ArrayList<Smartphone> availableSmartphones = new ArrayList<Smartphone>();
		for (int i = 0; i < electronics.size(); i++) {
			if (electronics.get(i).getClass().getSimpleName().equals("Smartphone") && !electronics.get(i).isSold()) {
				availableSmartphones.add((Smartphone) electronics.get(i));
				break;
			}
		}
		return availableSmartphones;
	}
	
	public ArrayList<Camera> getListOfAvailableCameras() {
		ArrayList<Camera> availableCameras = new ArrayList<Camera>();
		for (int i = 0; i < electronics.size(); i++) {
			if (electronics.get(i).getClass().getSimpleName().equals("Camera") && !electronics.get(i).isSold()) {
				availableCameras.add((Camera) electronics.get(i));
				break;
			}
		}
		return availableCameras;
	}
	
	public ArrayList<VideoGame> getListOfAvailableVideoGames() {
		ArrayList<VideoGame> availableVideoGames = new ArrayList<VideoGame>();
		for (int i = 0; i < electronics.size(); i++) {
			if (electronics.get(i).getClass().getSimpleName().equals("VideoGame") && !electronics.get(i).isSold()) {
				availableVideoGames.add((VideoGame) electronics.get(i));
				break;
			}
		}
		return availableVideoGames;
	}
	
	public void createDeal(Trader buyer, Electronic electronic) {
		Trader seller = findSellerByElectronicId(electronic.getId());
		int dealNo = electronic.getId();
		Date today = new Date();
		Calendar calender = Calendar.getInstance();
		calender.setTime(today);
		String month = String.valueOf(calender.get(Calendar.MONTH) + 1);
		String year = String.valueOf(calender.get(Calendar.YEAR));
		String dateCreated = month + "-" + year;
		Deal deal = new Deal(dateCreated, dealNo, buyer, seller, electronic);
		deal.createInvoice();
		deals.add(deal);
	}
	
	public Trader findSellerByElectronicId(int id) {
		for (int i = 0; i < traders.size(); i++) {
			for (int j = 0; j < traders.get(i).getElectronics().size(); j++) {
				if (traders.get(i).getElectronics().get(j).getId() == id) {
					return traders.get(i);
				}
			}
		}
		return null;
	}
	
	public ArrayList<Deal> getListOfClosedDeals() {
		ArrayList<Deal> closedDeals = new ArrayList<Deal>();
		for (int i = 0; i < deals.size(); i++) {
			if (deals.get(i).isClosed()) {
				closedDeals.add(deals.get(i));
			}
		}
		return closedDeals;
	}
	
	public ArrayList<Deal> getListOfUnclosedDeals() {
		ArrayList<Deal> closedDeals = new ArrayList<Deal>();
		for (int i = 0; i < deals.size(); i++) {
			if (!deals.get(i).isClosed()) {
				closedDeals.add(deals.get(i));
			}
		}
		return closedDeals;
	}
	
	public Deal findDeal(int id) {
		for (int i = 0; i < deals.size(); i++) {
			if (deals.get(i).getDealNo() == id) {
				return deals.get(i);
			}
		}
		return null;
	}
	
	public ArrayList<Invoice> getListOfUnpaidInvoices() {
		ArrayList<Invoice> unpaidInvoices = new ArrayList<Invoice>();
		for (int i = 0; i < deals.size(); i++) {
			if (!deals.get(i).getInvoice().isPaid()) {
				unpaidInvoices.add(deals.get(i).getInvoice());
			}
		}
		return unpaidInvoices;
	}
	
	public ArrayList<Deal> getDealsCreatedOnSpecificDate(String dateCreated) {
		ArrayList<Deal> dealsCreatedOnSpecificDate = new ArrayList<Deal>();
		for (int i = 0; i < deals.size(); i++) {
			if (deals.get(i).getDateCreated().equals(dateCreated)) {
				dealsCreatedOnSpecificDate.add(deals.get(i));
			}
		}
		return dealsCreatedOnSpecificDate;
	}
	
	public void closeDeal(int dealNo) {
		for (int i = 0; i < deals.size(); i++) {
			if (deals.get(i).getDealNo() == dealNo) {
				deals.get(i).setClosed();
				break;
			}
		}
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
