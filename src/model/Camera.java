package model;

public class Camera extends Electronic {
	private String pixelSize, zoom, lensSize;

	public Camera(int id, double price, String brand, String color, String pixelSize, String zoom, String lensSize) {
		super(id, price, brand, color);
		this.pixelSize = pixelSize;
		this.zoom = zoom;
		this.lensSize = lensSize;
		createDetails();
	}

	public String getPixelSize() {
		return pixelSize;
	}

	public void setPixelSize(String pixelSize) {
		this.pixelSize = pixelSize;
	}

	public String getZoom() {
		return zoom;
	}

	public void setZoom(String zoom) {
		this.zoom = zoom;
	}

	public String getLensSize() {
		return lensSize;
	}

	public void setLensSize(String lensSize) {
		this.lensSize = lensSize;
	}
	
	@Override
	public void createDetails() {
		details = "";
		details += "pixel size: " + pixelSize + ", ";
		details += "zoom: " + zoom + ", ";
		details += "lens size: " + lensSize;
	}
}
