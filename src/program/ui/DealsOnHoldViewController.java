package program.ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Deal;
import program.GUIApp;

public class DealsOnHoldViewController implements Initializable {

    @FXML
    private TableView<Deal> productCatalog;

    @FXML
    private TableColumn<Deal, Integer> idCol;

    @FXML
    private TableColumn<Deal, Double> priceCol;

    @FXML
    private TableColumn<Deal, String> brandCol;

    @FXML
    private TableColumn<Deal, String> colorCol;

    @FXML
    private TableColumn<Deal, String> detailsCol;
    
    @FXML
    private TableColumn<Deal, String> nameCol;

    @FXML
    private TableColumn<Deal, Integer> phoneCol;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		idCol.setCellValueFactory(new PropertyValueFactory<Deal, Integer>("id"));
		priceCol.setCellValueFactory(new PropertyValueFactory<Deal, Double>("price"));
		brandCol.setCellValueFactory(new PropertyValueFactory<Deal, String>("brand"));
		colorCol.setCellValueFactory(new PropertyValueFactory<Deal, String>("color"));
		detailsCol.setCellValueFactory(new PropertyValueFactory<Deal, String>("details"));
		nameCol.setCellValueFactory(new PropertyValueFactory<Deal, String>("name"));
		phoneCol.setCellValueFactory(new PropertyValueFactory<Deal, Integer>("phoneNumber"));
		
		ObservableList<Deal> dealsList = FXCollections.observableArrayList();
		for (Deal deal: GUIApp.getEQatarSystem().getListOfUnclosedDeals()) {
			if (deal.getBuyer().getId() == GUIApp.getCurrentUser().getId()) {
				dealsList.add(deal);
			}
		}
		productCatalog.setItems(dealsList);
	}
	
	@FXML
	private void backBtnAction() throws Exception {
		GUIApp.showBuyerView(GUIApp.getCurrentUser());
	}
}
