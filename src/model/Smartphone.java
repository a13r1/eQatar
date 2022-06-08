package model;

public class Smartphone extends Electronic {
	private String storage, screenSize, cameraResolution;

	public Smartphone(int id, double price, String brand, String color, String storage, String screenSize, String cameraResolution) {
		super(id, price, brand, color);
		this.storage = storage;
		this.screenSize = screenSize;
		this.cameraResolution = cameraResolution;
		createDetails();
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public String getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(String screenSize) {
		this.screenSize = screenSize;
	}

	public String getCameraResolution() {
		return cameraResolution;
	}

	public void setCameraResolution(String cameraResolution) {
		this.cameraResolution = cameraResolution;
	}
	
	@Override
	public void createDetails() {
		details = "";
		details += "storage: " + storage + ", ";
		details += "screen size: " + screenSize + ", ";
		details += "camera resolution: " + cameraResolution;
	}
}
