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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Deal;
import model.Electronic;
import program.GUIApp;

public class BuyerViewController implements Initializable {

	@FXML
	private ComboBox<String> typeComboBox;
	
	@FXML
	private TableView<Electronic> productCatalog;
	
	@FXML
	private TableColumn<Electronic, Integer> idCol;
	
	@FXML
	private TableColumn<Electronic, Double> priceCol;
	
	@FXML
	private TableColumn<Electronic, String> brandCol;
	
	@FXML
	private TableColumn<Electronic, String> colorCol;
	
	@FXML
	private TableColumn<Electronic, String> detailsCol;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		String[] types = {"All items", "Camera", "Smartphone", "Video Game"};
		ObservableList<String> boxList = FXCollections.observableArrayList(types);
		typeComboBox.setItems(boxList);
		typeComboBox.getSelectionModel().selectFirst();
		
		idCol.setCellValueFactory(new PropertyValueFactory<Electronic, Integer>("id"));
		priceCol.setCellValueFactory(new PropertyValueFactory<Electronic, Double>("price"));
		brandCol.setCellValueFactory(new PropertyValueFactory<Electronic, String>("brand"));
		colorCol.setCellValueFactory(new PropertyValueFactory<Electronic, String>("color"));
		detailsCol.setCellValueFactory(new PropertyValueFactory<Electronic, String>("details"));
		
		ObservableList<Electronic> electronicsList = FXCollections.observableArrayList();
		for (Electronic electronic: GUIApp.getEQatarSystem().getElectronics()) {
			if (!electronic.isSold()) {
				electronicsList.add(electronic);
			}
		}
		productCatalog.setItems(electronicsList);
	}
	
	@FXML
	private void typeSelectionAction() throws Exception {
		String type = typeComboBox.getValue();
		String className = "All items";
		if (type.equals("Camera")) {
			className = "Camera";
		} else if (type.equals("Smartphone")) {
			className = "Smartphone";
		} else if (type.equals("Video Game")) {
			className = "VideoGame";
		}
		
		if (type.equals("All items")) {
			ObservableList<Electronic> electronicsList = FXCollections.observableArrayList();
			for (Electronic electronic: GUIApp.getEQatarSystem().getElectronics()) {
				if (!electronic.isSold()) {
					electronicsList.add(electronic);
				}
			}
			productCatalog.setItems(electronicsList);
		} else {
			ObservableList<Electronic> electronicsList = FXCollections.observableArrayList();
			for (Electronic electronic: GUIApp.getEQatarSystem().getElectronics()) {
				if (!electronic.isSold() && electronic.getClass().getSimpleName().equals(className)) {
					electronicsList.add(electronic);
				}
			}
			productCatalog.setItems(electronicsList);
		}
	}
	
	@FXML
	private void buyBtnAction() throws Exception {
		Electronic electronic = productCatalog.getSelectionModel().getSelectedItem();
		if (electronic == null) {
			Alert alert = new Alert(AlertType.INFORMATION, "Please select an item to buy", ButtonType.OK);
			alert.showAndWait();
			return;
		}
		String brand = electronic.getBrand();
		String color = electronic.getColor();
		double price = electronic.getPrice();
		Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to buy this item: (brand " + brand + ", color " + color + ", price " + price + ")?", ButtonType.YES, ButtonType.NO);
		alert.showAndWait();
		if (alert.getResult() == ButtonType.NO) {
			return;
		}
		
		for (Deal deal: GUIApp.getEQatarSystem().getDeals()) {
			if (deal.getElectronicItem().getId() == electronic.getId() && deal.getBuyer().getId() == GUIApp.getCurrentUser().getId()) {
				alert = new Alert(AlertType.INFORMATION, "You have already confirmed a deal for this item!", ButtonType.OK);
				alert.showAndWait();
				return;
			}
		}
		GUIApp.getEQatarSystem().createDeal(GUIApp.getCurrentUser(), electronic);
		GUIApp.getFilesHandler().saveDeals();
		Deal deal = GUIApp.getEQatarSystem().findDeal(electronic.getId());
		GUIApp.showConfirmedDealView(deal);
	}
	
	@FXML
	private void dealsOnHoldBtnAction() throws Exception {
		GUIApp.showDealsOnHoldView();
	}
	
	@FXML
	private void logoutBtnAction() throws Exception {
		GUIApp.showMainView();
	}
}
