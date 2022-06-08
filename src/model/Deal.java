package model;

public class Deal {
	private String dateCreated;
	private int dealNo;
	private Trader buyer, seller;
	private Electronic electronicItem;
	private Invoice invoice;
	private boolean isClosed;
	
	public Deal(String dateCreated, int dealNo, Trader buyer, Trader seller, Electronic electronicItem) {
		this.dateCreated = dateCreated;
		this.dealNo = dealNo;
		this.buyer = buyer;
		this.seller = seller;
		this.electronicItem = electronicItem;
		isClosed = false;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public int getDealNo() {
		return dealNo;
	}

	public void setDealNo(int dealNo) {
		this.dealNo = dealNo;
	}

	public Trader getBuyer() {
		return buyer;
	}

	public void setBuyer(Trader buyer) {
		this.buyer = buyer;
	}

	public Trader getSeller() {
		return seller;
	}

	public void setSeller(Trader seller) {
		this.seller = seller;
	}

	public Electronic getElectronicItem() {
		return electronicItem;
	}

	public void setElectronicItem(Electronic electronicItem) {
		this.electronicItem = electronicItem;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public boolean isClosed() {
		return isClosed;
	}

	public void setClosed() {
		invoice.setPaid();
		this.isClosed = true;
	}
	
	public void createInvoice() {
		invoice = new Invoice(dealNo, electronicItem.getPrice(), dateCreated);
	}
	
	public int getId() {
		return electronicItem.getId();
	}
	
	public double getPrice() {
		return electronicItem.getPrice();
	}
	
	public String getBrand() {
		return electronicItem.getBrand();
	}
	
	public String getColor() {
		return electronicItem.getColor();
	}
	
	public String getDetails() {
		return electronicItem.getDetails();
	}
	
	public String getName() {
		return seller.getName();
	}
	
	public int getPhoneNumber() {
		return seller.getPhoneNumber();
	}
}
