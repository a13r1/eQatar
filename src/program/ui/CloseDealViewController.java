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

public class CloseDealViewController implements Initializable {

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
		idField.setText(String.valueOf(GUIApp.getCurrentElectronic().getId()));
		priceField.setText(String.valueOf(GUIApp.getCurrentElectronic().getPrice()));
		brandField.setText(GUIApp.getCurrentElectronic().getBrand());
		colorField.setText(GUIApp.getCurrentElectronic().getColor());
		
		String[] types = {"Camera", "Smartphone", "Video Game"};
		ObservableList<String> boxList = FXCollections.observableArrayList(types);
		typeComboBox.setItems(boxList);
		String type = GUIApp.getCurrentElectronic().getClass().getSimpleName();
		if (type.equals("Camera")) {
			typeComboBox.getSelectionModel().select("Camera");
			pixelSizeField.setText(((Camera) GUIApp.getCurrentElectronic()).getPixelSize());
			zoomField.setText(((Camera) GUIApp.getCurrentElectronic()).getZoom());
			lensSizeField.setText(((Camera) GUIApp.getCurrentElectronic()).getLensSize());
			smartphoneDetails.setVisible(false);
			videoGameDetails.setVisible(false);
			cameraDetails.setVisible(true);
		} else if (type.equals("Smartphone")) {
			typeComboBox.getSelectionModel().select("Smartphone");
			storageField.setText(((Smartphone) GUIApp.getCurrentElectronic()).getStorage());
			screenSizeField.setText(((Smartphone) GUIApp.getCurrentElectronic()).getScreenSize());
			cameraResolutionField.setText(((Smartphone) GUIApp.getCurrentElectronic()).getCameraResolution());
			cameraDetails.setVisible(false);
			videoGameDetails.setVisible(false);
			smartphoneDetails.setVisible(true);
		} else if (type.equals("VideoGame")) {
			typeComboBox.getSelectionModel().select("Video Game");
			memoryField.setText(((VideoGame) GUIApp.getCurrentElectronic()).getMemory());
			displayField.setText(((VideoGame) GUIApp.getCurrentElectronic()).getDisplay());
			connectivityField.setText(((VideoGame) GUIApp.getCurrentElectronic()).getConnectivity());
			controllerField.setText(((VideoGame) GUIApp.getCurrentElectronic()).getController());
			dimensionsField.setText(((VideoGame) GUIApp.getCurrentElectronic()).getDimensions());
			smartphoneDetails.setVisible(false);
			cameraDetails.setVisible(false);
			videoGameDetails.setVisible(true);
		}
	}
	
	@FXML
	private void cancelBtnAction() throws Exception {
		Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to cancel?", ButtonType.YES, ButtonType.NO);
		alert.showAndWait();
		if (alert.getResult() == ButtonType.YES) {
			GUIApp.showSellerView(GUIApp.getCurrentUser());
		}
	}
	
	@FXML
	private void submitBtnAction() throws Exception {
		Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to close this deal?", ButtonType.YES, ButtonType.NO);
		alert.showAndWait();
		if (alert.getResult() == ButtonType.YES) {
			GUIApp.getCurrentElectronic().setSold(true);
			GUIApp.getEQatarSystem().findDeal(GUIApp.getCurrentElectronic().getId()).setClosed();
			GUIApp.getFilesHandler().saveElectronics();
			GUIApp.getFilesHandler().saveDeals();
			alert = new Alert(AlertType.INFORMATION, "Success! A deal was closed. Products catalog is updated.", ButtonType.OK);
			alert.showAndWait();
			GUIApp.showSellerView(GUIApp.getCurrentUser());
		}
	}
}
