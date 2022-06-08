package model;

public class Invoice {
	private int invoiceNo;
	private double amount;
	private String dateCreated;
	private boolean isPaid;
	
	public Invoice(int invoiceNo, double amount, String dateCreated) {
		this.invoiceNo = invoiceNo;
		this.amount = amount;
		this.dateCreated = dateCreated;
		isPaid = false;
	}

	public int getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(int invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid() {
		this.isPaid = true;
	}
}
