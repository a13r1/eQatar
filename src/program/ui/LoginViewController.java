package program.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Trader;
import program.GUIApp;

public class LoginViewController {

	@FXML
	private TextField idField, phoneField;
	
	@FXML
	private void backBtnAction() throws Exception {
		GUIApp.showMainView();
	}
	
	@FXML
	private void loginBtnAction() throws Exception {
		String idStr = idField.getText();
		String phoneStr = phoneField.getText();
		if (idStr.isBlank() || phoneStr.isBlank()) {
			Alert alert = new Alert(AlertType.CONFIRMATION, "Please provide information for each field", ButtonType.OK);
			alert.showAndWait();
			return;
		}
		
		int id, phoneNumber;
		try {
			id = Integer.parseInt(idStr);
			phoneNumber = Integer.parseInt(phoneStr);
		} catch (NumberFormatException NFE) {
			Alert alert = new Alert(AlertType.CONFIRMATION, "Please provide numeric values for id and phone number", ButtonType.OK);
			alert.showAndWait();
			return;
		}
		
		Trader trader = GUIApp.getEQatarSystem().findTrader(id);
		if (trader == null) {
			Alert alert = new Alert(AlertType.CONFIRMATION, "Provided ID is not registered in the system", ButtonType.OK);
			alert.showAndWait();
			return;
		}
		if (trader.getPhoneNumber() != phoneNumber) {
			Alert alert = new Alert(AlertType.CONFIRMATION, "User phone number doesn't match with registered phone number of the given ID", ButtonType.OK);
			alert.showAndWait();
			return;
		}
		Alert alert = new Alert(AlertType.CONFIRMATION, "Logged in successfully!", ButtonType.OK);
		alert.showAndWait();
		if (trader.isSeller()) {
			GUIApp.showSellerView(trader);
		} else {
			GUIApp.showBuyerView(trader);
		}
	}
}
