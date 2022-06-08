package storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Camera;
import model.Deal;
import model.Electronic;
import model.Smartphone;
import model.Trader;
import model.VideoGame;
import program.GUIApp;

public class FilesHandler {
	/**
	 * Traders file format: id,name,isSeller,isBuyer,phoneNumber,address
	 * Electronics file format:
	 * 		1) Camera,id,price,brand,color,pixelSize,zoom,lensSize
	 * 		2) Smartphone,id,price,brand,color,storage,screenSize,cameraResolution
	 * 		3) VideoGame,id,price,brand,color,memory,display,connectivity,controller,dimensions
	 * Deals file format: dateCreated,dealNumber,buyerId,sellerId,electronicId,isClosed
	 */
	
	private Scanner scanner;
	
	public void saveTrader(Trader trader) {
		try {
			FileWriter fileWriter = new FileWriter("traders.txt", true);
			BufferedWriter writer = new BufferedWriter(fileWriter);
			
			StringBuilder traderRecord = new StringBuilder();
			traderRecord.append(trader.getId());
			traderRecord.append(",");
			traderRecord.append(trader.getName());
			traderRecord.append(",");
			traderRecord.append(trader.isSeller());
			traderRecord.append(",");
			traderRecord.append(trader.isBuyer());
			traderRecord.append(",");
			traderRecord.append(trader.getPhoneNumber());
			traderRecord.append(",");
			traderRecord.append(trader.getAddress());
			traderRecord.append("\n");
			
			writer.write(traderRecord.toString());
			writer.close();
			fileWriter.close();		
		} catch (IOException e) {
			System.out.println("I/O error");
		}
	}
	
	public void loadTraders() {
		ArrayList<Trader> traders = new ArrayList<Trader>();
		try {
			File tradersFile = new File("traders.txt");
			scanner = new Scanner(tradersFile);
			while (scanner.hasNextLine()) {
				String traderData = scanner.nextLine();
				if (!traderData.equals("\n")) {
					String[] info = traderData.split(",");
					int id = Integer.parseInt(info[0]);
					String name = info[1];
					boolean isSeller = Boolean.valueOf(info[2]);
					boolean isBuyer = !isSeller;
					int phoneNumber = Integer.parseInt(info[4]);
					String address = info[5];
					Trader trader = new Trader(id, name, isSeller, isBuyer, phoneNumber, address);
					traders.add(trader);
				}
			}
			scanner.close();
		} catch (IOException io) {
			System.out.println("I/O error");
		}
		GUIApp.getEQatarSystem().setTraders(traders);
	}
	
	public void saveElectronic(Electronic electronic) {
		try {
			FileWriter fileWriter = new FileWriter("electronics.txt", true);
			BufferedWriter writer = new BufferedWriter(fileWriter);
			
			StringBuilder electronicRecord = new StringBuilder();
			electronicRecord.append(electronic.getClass().getSimpleName());
			electronicRecord.append(",");
			electronicRecord.append(electronic.getId());
			electronicRecord.append(",");
			electronicRecord.append(electronic.getPrice());
			electronicRecord.append(",");
			electronicRecord.append(electronic.getBrand());
			electronicRecord.append(",");
			electronicRecord.append(electronic.getColor());
			electronicRecord.append(",");
			if (electronic.getClass().getSimpleName().equals("Camera")) {
				electronicRecord.append(((Camera) electronic).getPixelSize());
				electronicRecord.append(",");
				electronicRecord.append(((Camera) electronic).getZoom());
				electronicRecord.append(",");
				electronicRecord.append(((Camera) electronic).getLensSize());
			} else if (electronic.getClass().getSimpleName().equals("Smartphone")) {
				electronicRecord.append(((Smartphone) electronic).getStorage());
				electronicRecord.append(",");
				electronicRecord.append(((Smartphone) electronic).getScreenSize());
				electronicRecord.append(",");
				electronicRecord.append(((Smartphone) electronic).getCameraResolution());
			} else if (electronic.getClass().getSimpleName().equals("VideoGame")) {
				electronicRecord.append(((VideoGame) electronic).getMemory());
				electronicRecord.append(",");
				electronicRecord.append(((VideoGame) electronic).getDisplay());
				electronicRecord.append(",");
				electronicRecord.append(((VideoGame) electronic).getConnectivity());
				electronicRecord.append(",");
				electronicRecord.append(((VideoGame) electronic).getController());
				electronicRecord.append(",");
				electronicRecord.append(((VideoGame) electronic).getDimensions());
			}
			electronicRecord.append(",");
			electronicRecord.append(GUIApp.getCurrentUser().getId());
			electronicRecord.append(",");
			electronicRecord.append(electronic.isSold());
			electronicRecord.append("\n");
			writer.write(electronicRecord.toString());
			writer.close();
			fileWriter.close();		
		} catch (IOException e) {
			System.out.println("I/O error");
		}
	}
	
	public void saveElectronics() {
		try {
			FileWriter fileWriter = new FileWriter("electronics.txt");
			BufferedWriter writer = new BufferedWriter(fileWriter);
			writer.write("");
			writer.close();
			fileWriter.close();	
			fileWriter = new FileWriter("electronics.txt", true);
			writer = new BufferedWriter(fileWriter);
			for (Trader trader: GUIApp.getEQatarSystem().getTraders()) {
				for (Electronic electronic: trader.getElectronics()) {
					StringBuilder electronicRecord = new StringBuilder();
					if (electronic.getClass().getSimpleName().equals("Camera")) {
						electronicRecord.append("Camera");
					} else if (electronic.getClass().getSimpleName().equals("Smartphone")) {
						electronicRecord.append("Smartphone");
					} else if (electronic.getClass().getSimpleName().equals("VideoGame")) {
						electronicRecord.append("VideoGame");
					}
					electronicRecord.append(",");
					electronicRecord.append(electronic.getId());
					electronicRecord.append(",");
					electronicRecord.append(electronic.getPrice());
					electronicRecord.append(",");
					electronicRecord.append(electronic.getBrand());
					electronicRecord.append(",");
					electronicRecord.append(electronic.getColor());
					electronicRecord.append(",");
					if (electronic.getClass().getSimpleName().equals("Camera")) {
						electronicRecord.append(((Camera) electronic).getPixelSize());
						electronicRecord.append(",");
						electronicRecord.append(((Camera) electronic).getZoom());
						electronicRecord.append(",");
						electronicRecord.append(((Camera) electronic).getLensSize());
					} else if (electronic.getClass().getSimpleName().equals("Smartphone")) {
						electronicRecord.append(((Smartphone) electronic).getStorage());
						electronicRecord.append(",");
						electronicRecord.append(((Smartphone) electronic).getScreenSize());
						electronicRecord.append(",");
						electronicRecord.append(((Smartphone) electronic).getCameraResolution());
					} else if (electronic.getClass().getSimpleName().equals("VideoGame")) {
						electronicRecord.append(((VideoGame) electronic).getMemory());
						electronicRecord.append(",");
						electronicRecord.append(((VideoGame) electronic).getDisplay());
						electronicRecord.append(",");
						electronicRecord.append(((VideoGame) electronic).getConnectivity());
						electronicRecord.append(",");
						electronicRecord.append(((VideoGame) electronic).getController());
						electronicRecord.append(",");
						electronicRecord.append(((VideoGame) electronic).getDimensions());
					}
					electronicRecord.append(",");
					electronicRecord.append(trader.getId());
					electronicRecord.append(",");
					electronicRecord.append(electronic.isSold());
					electronicRecord.append("\n");
					writer.write(electronicRecord.toString());
				}
			}
			writer.close();
			fileWriter.close();		
		} catch (IOException e) {
			System.out.println("I/O error");
		}
	}
	
	public void loadElectronics() {
		try {
			File electronicsFile = new File("electronics.txt");
			scanner = new Scanner(electronicsFile);
			while (scanner.hasNextLine()) {
				String electronicData = scanner.nextLine();
				if (!electronicData.equals("\n")) {
					String[] info = electronicData.split(",");
					String type = info[0];
					int id = Integer.parseInt(info[1]);
					Double price = Double.parseDouble(info[2]);
					String brand = info[3];
					String color = info[4];
					int sellerID;
					if (type.equals("Camera")) {
						String pixelSize = info[5];
						String zoom = info[6];
						String lensSize = info[7];
						sellerID = Integer.parseInt(info[8]);
						Camera camera = new Camera(id, price, brand, color, pixelSize, zoom, lensSize);
						boolean isSold = Boolean.parseBoolean(info[9]);
						camera.setSold(isSold);
						GUIApp.getEQatarSystem().findTrader(sellerID).addElectronic(camera);
						GUIApp.getEQatarSystem().addElectronic(camera);
					} else if (type.equals("Smartphone")) {
						String storage = info[5];
						String screenSize = info[6];
						String cameraResolution = info[7];
						sellerID = Integer.parseInt(info[8]);
						Smartphone smartphone = new Smartphone(id, price, brand, color, storage, screenSize, cameraResolution);
						boolean isSold = Boolean.parseBoolean(info[9]);
						smartphone.setSold(isSold);
						GUIApp.getEQatarSystem().findTrader(sellerID).addElectronic(smartphone);
						GUIApp.getEQatarSystem().addElectronic(smartphone);
					} else {
						String memory = info[5];
						String display = info[6];
						String connectivity = info[7];
						String controller = info[8];
						String dimensions = info[9];
						sellerID = Integer.parseInt(info[10]);
						VideoGame videoGame = new VideoGame(id, price, brand, color, memory, display, connectivity, controller, dimensions);
						boolean isSold = Boolean.parseBoolean(info[11]);
						videoGame.setSold(isSold);
						GUIApp.getEQatarSystem().findTrader(sellerID).addElectronic(videoGame);
						GUIApp.getEQatarSystem().addElectronic(videoGame);
					}
				}
			}
			scanner.close();
		} catch (IOException io) {
			System.out.println("I/O error");
		}
	}
	
	public void loadDeals() {
		ArrayList<Deal> deals = new ArrayList<Deal>();
		try {
			File dealsFile = new File("deals.txt");
			scanner = new Scanner(dealsFile);
			while (scanner.hasNextLine()) {
				String dealData = scanner.nextLine();
				if (!dealData.equals("\n")) {
					String[] info = dealData.split(",");
					String date = info[0];
					int dealNo = Integer.parseInt(info[1]);
					int buyerId = Integer.parseInt(info[2]);
					int sellerId = Integer.parseInt(info[3]);
					int electronicId = Integer.parseInt(info[4]);
					boolean isClosed = Boolean.valueOf(info[5]);
					
					Trader buyer = GUIApp.getEQatarSystem().findTrader(buyerId);
					Trader seller = GUIApp.getEQatarSystem().findTrader(sellerId);
					Electronic electrnoic = GUIApp.getEQatarSystem().findElectronic(electronicId);
					Deal deal = new Deal(date, dealNo, buyer, seller, electrnoic);
					deal.createInvoice();
					if (isClosed) {
						deal.setClosed();
					}
					deals.add(deal);
				}
			}
			scanner.close();
		} catch (IOException io) {
			System.out.println("I/O error");
		}
		GUIApp.getEQatarSystem().setDeals(deals);
	}
	
	public void saveDeals() {
		try {
			FileWriter fileWriter = new FileWriter("deals.txt");
			BufferedWriter writer = new BufferedWriter(fileWriter);
			writer.write("");
			writer.close();
			fileWriter.close();	
			fileWriter = new FileWriter("deals.txt", true);
			writer = new BufferedWriter(fileWriter);
			for (Deal deal: GUIApp.getEQatarSystem().getDeals()) {
				StringBuilder dealRecord = new StringBuilder();
				dealRecord.append(deal.getDateCreated());
				dealRecord.append(",");
				dealRecord.append(deal.getDealNo());
				dealRecord.append(",");
				dealRecord.append(deal.getBuyer().getId());
				dealRecord.append(",");
				dealRecord.append(deal.getSeller().getId());
				dealRecord.append(",");
				dealRecord.append(deal.getElectronicItem().getId());
				dealRecord.append(",");
				dealRecord.append(deal.isClosed());
				dealRecord.append("\n");
				writer.write(dealRecord.toString());
			}
			writer.close();
			fileWriter.close();		
		} catch (IOException e) {
			System.out.println("I/O error");
		}
	}
}
