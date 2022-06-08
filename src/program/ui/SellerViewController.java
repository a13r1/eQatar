package program.ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Electronic;
import program.GUIApp;

public class SellerViewController implements Initializable {
	
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
		idCol.setCellValueFactory(new PropertyValueFactory<Electronic, Integer>("id"));
		priceCol.setCellValueFactory(new PropertyValueFactory<Electronic, Double>("price"));
		brandCol.setCellValueFactory(new PropertyValueFactory<Electronic, String>("brand"));
		colorCol.setCellValueFactory(new PropertyValueFactory<Electronic, String>("color"));
		detailsCol.setCellValueFactory(new PropertyValueFactory<Electronic, String>("details"));
		
		ObservableList<Electronic> electronicsList = FXCollections.observableArrayList();
		for (Electronic electronic: GUIApp.getCurrentUser().getElectronics()) {
			if (!electronic.isSold()) {
				electronicsList.add(electronic);
			}
		}
		productCatalog.setItems(electronicsList);
	}
	
	@FXML
	private void logoutBtnAction() throws Exception {
		GUIApp.showMainView();
	}
	
	@FXML
	private void sellBtnAction() throws Exception {
		GUIApp.showSellNewItemView();
	}
	
	@FXML
	private void updateBtnAction() throws Exception {
		Electronic electronic = productCatalog.getSelectionModel().getSelectedItem();
		if (electronic == null) {
			Alert alert = new Alert(AlertType.CONFIRMATION, "Please select an item to update", ButtonType.OK);
			alert.showAndWait();
			return;
		}
		GUIApp.showUpdateItemView(electronic);
	}
	
	@FXML
	private void closeDealBtnAction() throws Exception {
		Electronic electronic = productCatalog.getSelectionModel().getSelectedItem();
		if (electronic == null) {
			Alert alert = new Alert(AlertType.CONFIRMATION, "Please select an item to close its deal", ButtonType.OK);
			alert.showAndWait();
			return;
		}
		GUIApp.showCloseDealView(electronic);
	}
	
	@FXML
	private void saveBtnAction() throws Exception {
		productCatalog.refresh();
	}
}
