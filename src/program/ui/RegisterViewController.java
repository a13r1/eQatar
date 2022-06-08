package program.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import model.Trader;
import javafx.scene.control.Alert.AlertType;
import program.GUIApp;

public class RegisterViewController {
	
	@FXML
	private TextField idField, nameField, phoneField, addressField;
	
	@FXML
	private CheckBox sellerCheckBox;
	
	@FXML
	private void cancelBtnAction() throws Exception {
		Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to cancel this form?", ButtonType.YES, ButtonType.NO);
		alert.showAndWait();
		if (alert.getResult() == ButtonType.YES) {
			GUIApp.showMainView();
		}
	}

	@FXML
	private void submitBtnAction() throws Exception {
		String idStr = idField.getText();
		String name = nameField.getText();
		String phoneStr = phoneField.getText();
		String address = addressField.getText();
		boolean isSeller = sellerCheckBox.isSelected();
		
		if (idStr.isBlank() || name.isBlank() || phoneStr.isBlank() || address.isBlank()) {
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
		
		for (Trader trader: GUIApp.getEQatarSystem().getTraders()) {
			if (trader.getId() == id || trader.getPhoneNumber() == phoneNumber) {
				Alert alert = new Alert(AlertType.CONFIRMATION, "ID or Phone number is already registered in the system!", ButtonType.OK);
				alert.showAndWait();
				return;
			}
		}
		
		Trader newTrader = new Trader(id, name, isSeller, !isSeller, phoneNumber, address);
		GUIApp.getEQatarSystem().addTrader(newTrader);
		// TODO store trader to traders file
		GUIApp.getFilesHandler().saveTrader(newTrader);
		Alert alert = new Alert(AlertType.CONFIRMATION, "User is registered successfully!", ButtonType.OK);
		alert.showAndWait();
		GUIApp.showMainView();
	}
}
