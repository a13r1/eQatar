package program.ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import model.Deal;
import model.Electronic;
import program.GUIApp;

public class ConfirmedDealViewController implements Initializable {

    @FXML
    private TextArea describtionLbl;

    @FXML
    private Label dealNoLbl;

    @FXML
    private Label dateLbl;

    @FXML
    private Label invoiceNoLbl;

    @FXML
    private Label priceLbl;

    @FXML
    private Label statusLbl;

    @FXML
    private Label sellerNameLbl;

    @FXML
    private Label sellerPhoneLbl;

    @FXML
    private Label sellerAddressLbl;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Deal deal = GUIApp.getCurrentDeal();
		dealNoLbl.setText(String.valueOf(deal.getDealNo()));
		dateLbl.setText(deal.getDateCreated());
		Electronic electronic = deal.getElectronicItem();
		String describtion;
		if (electronic.getClass().getSimpleName().equals("Camera")) {
			describtion = "Camera";
		} else if (electronic.getClass().getSimpleName().equals("Smartphone")) {
			describtion = "Smartphone";
		} else {
			describtion = "Video Game";
		}
		describtion += "\nID: " + electronic.getId();
		describtion += ", Brand: " + electronic.getBrand();
		describtion += ", Color: " + electronic.getColor();
		describtion += "\nDetails:\n" + electronic.getDetails();
		describtionLbl.setText(describtion);
		invoiceNoLbl.setText(String.valueOf(deal.getInvoice().getInvoiceNo()));
		priceLbl.setText(String.valueOf(deal.getInvoice().getAmount()));
		statusLbl.setText("unpaid");
		sellerNameLbl.setText(deal.getSeller().getName());
		sellerPhoneLbl.setText(String.valueOf(deal.getSeller().getPhoneNumber()));
		sellerAddressLbl.setText(deal.getSeller().getAddress());
	}

	@FXML
	private void okBtnAction() throws Exception {
		GUIApp.showBuyerView(GUIApp.getCurrentUser());
	}
}
