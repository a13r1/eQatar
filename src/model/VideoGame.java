package model;

public class VideoGame extends Electronic {
	private String memory, display, connectivity, controller, dimensions;

	public VideoGame(int id, double price, String brand, String color, String memory, String display, String connectivity, String controller, String dimensions) {
		super(id, price, brand, color);
		this.memory = memory;
		this.display = display;
		this.connectivity = connectivity;
		this.controller = controller;
		this.dimensions = dimensions;
		createDetails();
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getConnectivity() {
		return connectivity;
	}

	public void setConnectivity(String connectivity) {
		this.connectivity = connectivity;
	}

	public String getController() {
		return controller;
	}

	public void setController(String controller) {
		this.controller = controller;
	}

	public String getDimensions() {
		return dimensions;
	}

	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}
	
	@Override
	public void createDetails() {
		details = "";
		details += "memory: " + memory + ", ";
		details += "display: " + display + ", ";
		details += "connectivity: " + connectivity + ", ";
		details += "controller: " + controller + ", ";
		details += "dimensions: " + dimensions;
	}
}
