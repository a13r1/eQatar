package program;

import model.Camera;
import model.Electronic;
import model.Smartphone;
import model.Trader;

public class Phase1Test {

	public static void main(String[] args) {
		Electronic camera1 = new Camera(1, 50.3, "sony", "black", "1080x720", "3x", "5 inches");
		Electronic camera2 = new Camera(2, 100.1, "sony", "white", "1080x720", "5x", "7 inches");
		Trader trader = new Trader(1, "ali", true, false, 1246546, "35 st. aeda kadima");
		trader.addElectronic(camera1);
		trader.addElectronic(camera2);
		for (Electronic e: trader.getElectronics()) {
			System.out.println(e.getPrice());
		}
		Electronic camera3 = new Camera(2, 113.13, "sony", "white", "1080x720", "5x", "7 inches");
		trader.modifyProperties(camera3);
		for (Electronic e: trader.getElectronics()) {
			System.out.println(e.getPrice());
		}
		
		Electronic smartphone1 = new Smartphone(1, 131313, null, null, null, null, null);
		trader.modifyProperties(smartphone1);
		for (Electronic e: trader.getElectronics()) {
			System.out.println(e.getPrice());
		}
	}

}
