package program.ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import model.Camera;
import model.Smartphone;
import model.VideoGame;
import program.GUIApp;

public class SellNewItemViewController implements Initializable {

	@FXML
	private ComboBox<String> typeComboBox;
	
	@FXML
	private AnchorPane cameraDetails;
	
	@FXML
	private AnchorPane smartphoneDetails;
	
	@FXML
	private AnchorPane videoGameDetails;
	
	@FXML
	private TextField idField, priceField, brandField, colorField;
	
	@FXML
	private TextField pixelSizeField, zoomField, lensSizeField;
	
	@FXML
	private TextField storageField, screenSizeField, cameraResolutionField;
	
	@FXML
	private TextField memoryField, displayField, connectivityField, controllerField, dimensionsField;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		String[] types = {"Camera", "Smartphone", "Video Game"};
		ObservableList<String> boxList = FXCollections.observableArrayList(types);
		typeComboBox.setItems(boxList);
		typeComboBox.getSelectionModel().selectFirst();
	}
	
	@FXML
	private void typeSelectionAction() throws Exception {
		String type = typeComboBox.getValue();
		if (type.equals("Camera")) {
			smartphoneDetails.setVisible(false);
			videoGameDetails.setVisible(false);
			cameraDetails.setVisible(true);
		} else if (type.equals("Smartphone")) {
			videoGameDetails.setVisible(false);
			cameraDetails.setVisible(false);
			smartphoneDetails.setVisible(true);
		} else if (type.equals("Video Game")) {
			smartphoneDetails.setVisible(false);
			cameraDetails.setVisible(false);
			videoGameDetails.setVisible(true);
		}
	}
	
	@FXML
	private void cancelBtnAction() throws Exception {
		Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to cancel this form?", ButtonType.YES, ButtonType.NO);
		alert.showAndWait();
		if (alert.getResult() == ButtonType.YES) {
			GUIApp.showSellerView(GUIApp.getCurrentUser());
		}
	}
	
	@FXML
	private void submitBtnAction() throws Exception {
		String idStr = idField.getText();
		String priceStr = priceField.getText();
		String brand = brandField.getText();
		String color = colorField.getText();
		if (idStr.isBlank() || priceStr.isBlank() || brand.isBlank() || color.isBlank()) {
			Alert alert = new Alert(AlertType.CONFIRMATION, "Please provide information for each field", ButtonType.OK);
			alert.showAndWait();
			return;
		}
		
		int id;
		double price;
		try {
			id = Integer.parseInt(idStr);
			price = Double.parseDouble(priceStr);
		} catch (NumberFormatException NFE) {
			Alert alert = new Alert(AlertType.CONFIRMATION, "Please provide numeric values for id and price", ButtonType.OK);
			alert.showAndWait();
			return;
		}
		
		if (GUIApp.getEQatarSystem().findElectronic(id) != null) {
			Alert alert = new Alert(AlertType.CONFIRMATION, "This electronic id already exists!", ButtonType.OK);
			alert.showAndWait();
			return;
		}
		
		String type = typeComboBox.getValue();
		if (type.equals("Camera")) {
			String pixelSize = pixelSizeField.getText();
			String zoom = zoomField.getText();
			String lensSize = lensSizeField.getText();
			if (pixelSize.isBlank() || zoom.isBlank() || lensSize.isBlank()) {
				Alert alert = new Alert(AlertType.CONFIRMATION, "Please provide information for each field", ButtonType.OK);
				alert.showAndWait();
				return;
			}
			Camera camera = new Camera(id, price, brand, color, pixelSize, zoom, lensSize);
			GUIApp.getEQatarSystem().addElectronic(camera);
			GUIApp.getCurrentUser().addElectronic(camera);
			GUIApp.getFilesHandler().saveElectronic(camera);
		} else if (type.equals("Smartphone")) {
			String storage = storageField.getText();
			String screenSize = screenSizeField.getText();
			String cameraResolution = cameraResolutionField.getText();
			if (storage.isBlank() || screenSize.isBlank() || cameraResolution.isBlank()) {
				Alert alert = new Alert(AlertType.CONFIRMATION, "Please provide information for each field", ButtonType.OK);
				alert.showAndWait();
				return;
			}
			Smartphone smartphone = new Smartphone(id, price, brand, color, storage, screenSize, cameraResolution);
			GUIApp.getEQatarSystem().addElectronic(smartphone);
			GUIApp.getCurrentUser().addElectronic(smartphone);
			GUIApp.getFilesHandler().saveElectronic(smartphone);
		} else if (type.equals("Video Game")) {
			String memory = memoryField.getText();
			String display = displayField.getText();
			String connectivity = connectivityField.getText();
			String controller = controllerField.getText();
			String dimensions = dimensionsField.getText();
			if (memory.isBlank() || display.isBlank() || connectivity.isBlank() || controller.isBlank() || dimensions.isBlank()) {
				Alert alert = new Alert(AlertType.CONFIRMATION, "Please provide information for each field", ButtonType.OK);
				alert.showAndWait();
				return;
			}
			VideoGame videoGame = new VideoGame(id, price, brand, color, memory, display, connectivity, controller, dimensions);
			GUIApp.getEQatarSystem().addElectronic(videoGame);
			GUIApp.getCurrentUser().addElectronic(videoGame);
			GUIApp.getFilesHandler().saveElectronic(videoGame);
		}
		GUIApp.showSellerView(GUIApp.getCurrentUser());
	}
}
