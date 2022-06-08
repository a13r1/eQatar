package program;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Deal;
import model.EQatarSystem;
import model.Electronic;
import model.Trader;
import storage.FilesHandler;

public class GUIApp extends Application {
	private static EQatarSystem eQatarSystem;
	private static FilesHandler filesHandler;
	private static Stage primaryStage;
	private static AnchorPane main;
	private static Trader currentUser;
	private static Electronic currentElectronic;
	private static Deal currentDeal;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		eQatarSystem = new EQatarSystem();
		filesHandler = new FilesHandler();
		filesHandler.loadTraders();
		filesHandler.loadElectronics();
		filesHandler.loadDeals();
		GUIApp.primaryStage = primaryStage;
		GUIApp.primaryStage.setResizable(false);
		showMainView();
	}
	
	public static EQatarSystem getEQatarSystem() {
		return eQatarSystem;
	}
	
	public static FilesHandler getFilesHandler() {
		return filesHandler;
	}
	
	public static Trader getCurrentUser() {
		return currentUser;
	}
	
	public static Electronic getCurrentElectronic() {
		return currentElectronic;
	}
	
	public static Deal getCurrentDeal() {
		return currentDeal;
	}
	
	public static void showMainView() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(GUIApp.class.getResource("ui/MainView.fxml"));
		main = loader.load();
		Scene scene = new Scene(main);
		GUIApp.primaryStage.setTitle("eQatar");
		primaryStage.getIcons().add(new Image(("file:icons/logo.jpg")));
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void showRegisterView() throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(GUIApp.class.getResource("ui/RegisterView.fxml"));
		AnchorPane anchorPane = loader.load();
		Scene scene = new Scene(anchorPane);
		GUIApp.primaryStage.setTitle("Register");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void showLoginView() throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(GUIApp.class.getResource("ui/LoginView.fxml"));
		AnchorPane anchorPane = loader.load();
		Scene scene = new Scene(anchorPane);
		GUIApp.primaryStage.setTitle("Login");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void showSellerView(Trader trader) throws Exception {
		currentUser = trader;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(GUIApp.class.getResource("ui/SellerView.fxml"));
		AnchorPane anchorPane = loader.load();
		Scene scene = new Scene(anchorPane);
		GUIApp.primaryStage.setTitle("Seller: " + trader.getName());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void showBuyerView(Trader trader) throws Exception {
		currentUser = trader;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(GUIApp.class.getResource("ui/BuyerView.fxml"));
		AnchorPane anchorPane = loader.load();
		Scene scene = new Scene(anchorPane);
		GUIApp.primaryStage.setTitle("Buyer: " + trader.getName());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void showSellNewItemView() throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(GUIApp.class.getResource("ui/SellNewItemView.fxml"));
		AnchorPane anchorPane = loader.load();
		Scene scene = new Scene(anchorPane);
		GUIApp.primaryStage.setTitle("Sell a new item");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void showUpdateItemView(Electronic electronic) throws Exception {
		currentElectronic = electronic;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(GUIApp.class.getResource("ui/UpdateItemView.fxml"));
		AnchorPane anchorPane = loader.load();
		Scene scene = new Scene(anchorPane);
		GUIApp.primaryStage.setTitle("Update item");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void showCloseDealView(Electronic electronic) throws Exception {
		currentElectronic = electronic;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(GUIApp.class.getResource("ui/CloseDealView.fxml"));
		AnchorPane anchorPane = loader.load();
		Scene scene = new Scene(anchorPane);
		GUIApp.primaryStage.setTitle("Close a deal");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void showConfirmedDealView(Deal deal) throws Exception {
		currentDeal = deal;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(GUIApp.class.getResource("ui/ConfirmedDealView.fxml"));
		AnchorPane anchorPane = loader.load();
		Scene scene = new Scene(anchorPane);
		GUIApp.primaryStage.setTitle("Confirmed Deal");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void showDealsOnHoldView() throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(GUIApp.class.getResource("ui/DealsOnHoldView.fxml"));
		AnchorPane anchorPane = loader.load();
		Scene scene = new Scene(anchorPane);
		GUIApp.primaryStage.setTitle("Deals On Hold");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
