package program.ui;

import javafx.fxml.FXML;
import program.GUIApp;

public class MainViewController {
	
	@FXML
	private void registerBtnAction() throws Exception {
		GUIApp.showRegisterView();
	}

	@FXML
	private void loginBtnAction() throws Exception {
		GUIApp.showLoginView();
	}
}
