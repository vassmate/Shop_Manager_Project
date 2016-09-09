package runner;

import java.util.Iterator;
import java.util.Vector;

import plaza.PlazaImpl;
import shop.Product;
import shop.ProductFactory;
import shop.Shop;
import shop.Shop.ProductIterator;
import shop.Shop.ShopRegistration;
import shop.ShopException;
import shop.exception.ShopClosedException;
import shop.product.Milk;

public class Runner {

	public static void main(String[] args) {
		welcomeMessage();

		ProductFactory pFactory = new ProductFactory();

		// LongLife milks
		Product milk1 = pFactory.makeNewLongLifeMilk("Falusi", "2016-11-20", Milk.CUP, Milk.SEMI_SKIMMED);
		Product milk2 = pFactory.makeNewWholeLLMilk("Riska", "2016-05-10", Milk.HALF_LITER);
		Product milk3 = pFactory.makeNewSemiSkimmedLLMilk("Riska", "2016-05-10", Milk.LITER);
		Product milk4 = pFactory.makeNewLiterSemiSkimmedLLMilk("Mizo", "2016-06-15");
		Product milk5 = pFactory.makeNewLiterWholeLLMilk("Spar", "2016-08-20");

		// SemiLongLife milks
		Product milk6 = pFactory.makeNewSemiLongLifeMilk("Milk", "2016-12-20", Milk.LITER, Milk.WHOLE);
		Product milk7 = pFactory.makeNewWholeSLLMilk("Mizo", "2016-05-10", Milk.HALF_LITER);
		Product milk8 = pFactory.makeNewSemiSkimmedSLLMilk("Riska", "2016-05-10", Milk.LITER);
		Product milk9 = pFactory.makeNewLiterSemiSkimmedSLLMilk("Tej", "2016-12-20");
		Product milk10 = pFactory.makeNewLiterWholeSLLMilk("Riska", "2016-05-10");
		Product milk11 = pFactory.makeNewLiterSemiSkimmedSLLMilk("TehenMilk", "2016-05-10");

		// Cheese
		Product cheese1 = pFactory.makeNewCheese("Tehen", "2016-12-01", 12.6, 10.2);
		Product cheese2 = pFactory.makeNewCheese("Sajt", "2016-10-01", 11.4, 5.5);

		// Soap
		Product soap1 = pFactory.makeNewSoap("Szappan", 'A');
		Product soap2 = pFactory.makeNewSoap("Soap", 'B');

		Shop cShop = new Shop("Coop", "Egyenes utca 1.", "K. Jozsi", "C:/Users/Public/Documents");
		Shop sShop = new Shop("Spar", "Egyenes utca 1.", "S. Jozsi", "C:/Users/Public/Documents");
		Shop vShop = new Shop("VegyesBolt", "Egyenes utca 1.", "V. Jozsi", "C:/Users/Public/Documents");
		Shop tShop = new Shop("TheShop", "Egyenes utca 1.", "T. Jozsi", "C:/Users/Public/Documents");

		Vector<Shop> boltok = new Vector<Shop>();
		boltok.add(cShop);
		boltok.add(sShop);
		boltok.add(vShop);
		boltok.add(tShop);

		PlazaImpl centrum = new PlazaImpl(boltok);

		Iterator<Shop> shopIter = centrum.getAllShops();
		Shop currentShop;

		while (shopIter.hasNext()) {
			currentShop = shopIter.next();
			currentShop.open();
			currentShop.addNewProduct(milk1, 25, 250);
			currentShop.addNewProduct(milk2, 20, 300);
			currentShop.addNewProduct(milk3, 13, 150);
			currentShop.addNewProduct(milk4, 11, 200);
			currentShop.addNewProduct(milk5, 40, 120);
			currentShop.addNewProduct(milk6, 22, 410);
			currentShop.addNewProduct(milk7, 12, 320);
			currentShop.addNewProduct(milk8, 32, 510);
			currentShop.addNewProduct(milk9, 44, 310);
			currentShop.addNewProduct(milk10, 36, 210);
			currentShop.addNewProduct(milk11, 30, 110);
			currentShop.addNewProduct(cheese1, 20, 620);
			currentShop.addNewProduct(cheese2, 20, 750);
			currentShop.addNewProduct(soap1, 20, 650);
			currentShop.addNewProduct(soap2, 20, 450);

			// Print all shops. Set it to true if you want to print all the shop
			// data.
			printShop(currentShop, false);

			/*
			 * Already close the shop because of the writer streams not working
			 * properly otherwise. Will fix it later.
			 */
			currentShop.close();

			// Check command line arguments
			checkArgs(args, currentShop);
		}

		centrum.close();
	}

	private static void welcomeMessage() {
		System.out.println("\n>Hi! This is a Shop Manager Project Apllictaion."
				+ "\n>The program has already made a few logfiles at the default log directory which is:"
				+ "\n\t\tC:/Users/Public/Documents/smp_log" + "\n\tor:" + "\n\t\t/home/smp_log "
				+ "\n\tif you are using Linux."
				+ "\n>If you would like to see their content than re-run this application with a command line argument from below.");
		System.out.println("\n>Available command line arguments:" + "\n\tlog.show = printLogfileContent(fShop)"
				+ "\n\tlog.show.code1 = printLogfileContent(shop.getReplenishLogs())"
				+ "\n\tlog.show.code2 = printLogfileContent(shop.getRemoveLogs())"
				+ "\n\tlog.show.code3 = printLogfileContent(shop.getBuyLogs())"
				+ "\n\tlog.show.code4 = printLogfileContent(shop.getProductListRequestLogs())");
		System.out.println("\n>If you want to clear the logs, than use:" + "\n\tlog.clear = fShop.clearLogging()");
	}

	private static void printShop(Shop shop, boolean doPrint) {
		try {
			if (doPrint) {
				if (shop.isOpen()) {
					ProductIterator pIter = shop.new ProductIterator(shop.getProductsIterator());
					System.out.println(shop + "\n_Products:");
					while (pIter.hasNext()) {
						Product currentIterProduct = (Product) pIter.next();
						ShopRegistration currentShopReg = shop.getProductStock().get(currentIterProduct.getBarcode());
						System.out.println(currentShopReg);
					}
				} else {
					throw new ShopClosedException();
				}
			}
		} catch (ShopException ex) {
			System.out.println(ex);
		}
	}

	private static void printLogfileContent(Shop shop, Iterator<String> specifiedIterator) {
		System.out.println("\n>>" + shop.getName() + " logfile's content:");
		Iterator<String> logs = specifiedIterator;
		while (logs.hasNext()) {
			System.out.println(logs.next());
		}
	}

	private static void checkArgs(String[] args, Shop shop) {
		if (args.length != 0) {
			for (String arg : args) {
				if (arg.equals("log.clear")) {
					shop.clearLogging();
				}
				if (arg.equals("log.show")) {
					printLogfileContent(shop, shop.getLogs());
				}
				if (arg.equals("log.show.code1")) {
					printLogfileContent(shop, shop.getReplenishLogs());
				}
				if (arg.equals("log.show.code2")) {
					printLogfileContent(shop, shop.getRemoveLogs());
				}
				if (arg.equals("log.show.code3")) {
					printLogfileContent(shop, shop.getBuyLogs());
				}
				if (arg.equals("log.show.code4")) {
					printLogfileContent(shop, shop.getProductListRequestLogs());
				}
			}
		}
	}
}
