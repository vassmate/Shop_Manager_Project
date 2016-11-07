package runner;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import plaza.PlazaImpl;
import shop.Product;
import shop.ProductFactory;
import shop.Shop;
import shop.product.Milk;

/**
 * @author VMate
 *
 */
public class RunnerGui {

	private JFrame frame;
	private static PlazaImpl plaza = initialzePlaza();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RunnerGui window = new RunnerGui();
					JTextArea textArea = new JTextArea();
					File fontFile = new File("font_types\\consola.ttf");
					Font newFont = Font.createFont(0, fontFile);
					Font resizedFont = newFont.deriveFont((float) 15);
					textArea.setFont(resizedFont);
					textArea.setEditable(false);
					window.frame.setVisible(true);
					window.frame.setTitle("Shop Management Application");
					window.frame.getContentPane().add(textArea);
					window.frame.setLocation(0, 0);
					window.frame.getContentPane().add(new JScrollPane(textArea));
					textArea.append(getWelcomeMsg().toString());
					/**
					 * Assemble user text field
					 */
					JTextField userText = new JTextField();
					userText.setEditable(true);
					userText.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							textArea.setText(null);
							String[] inputsFromUser = event.getActionCommand().split(" ");
							for (String userInput : inputsFromUser) {
								if (userInput.equals("help")) {
									textArea.append(getHelpMsg().toString());
								} else if (userInput.equals("init.plaza")) {
									plaza = initialzePlaza();
									textArea.append("Plaza with shops has been made!");
								} else {
									StringBuilder logText = getLogfileContentText(plaza, userInput);
									if (logText != null) {
										// textArea.append("\n");
										textArea.append(logText.toString());
									}
								}
							}
							userText.setText("");
						}
					});
					window.frame.getContentPane().add(userText, BorderLayout.NORTH);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the application.
	 */
	public RunnerGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 720);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private static StringBuilder getWelcomeMsg() {
		StringBuilder sb = new StringBuilder();
		sb.append(">Welcome in the Shop Management Application!");
		sb.append("\n>The program has already made a few logfiles at the default log directory which is:");
		sb.append("\n\t\tC:/Users/Public/Documents/smp_log");
		sb.append("\n\tor:");
		sb.append("\n\t\t/home/smp_log ");
		sb.append("\n\tif you are using Linux.\n");
		sb.append(getHelpMsg());
		return sb;
	}

	private static StringBuilder getHelpMsg() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(">Available commands:");
		sBuilder.append("\n\tshow.log - Show all logs.");
		sBuilder.append("\n\tshow.code1 - Show replenish logs.");
		sBuilder.append("\n\tshow.code2 - Show remove logs.");
		sBuilder.append("\n\tshow.code3 - Show buy logs.");
		sBuilder.append("\n\tshow.code4 - Show log request logs.");
		sBuilder.append("\n\tclear.log: - Clear the logs.");
		sBuilder.append("\n\thelp - Show available commands.");
		return sBuilder;
	}

	private static StringBuilder getLogfileContentText(PlazaImpl plaza, String userInput) {
		StringBuilder sb = new StringBuilder();
		Iterator<Shop> plazaIter = plaza.getAllShops();
		Shop currentShop;
		Iterator<String> currentShopLogs = null;
		while (plazaIter.hasNext()) {
			currentShop = plazaIter.next();
			if (userInput.equals("clear.log")) {
				currentShop.clearLogging();
				sb.append("\n>>" + currentShop.getName() + " logfile's content:");
				sb.append("\nLogfile cleared!");
			}
			if (userInput.equals("show.log")) {
				currentShopLogs = currentShop.getLogs();
			}
			if (userInput.equals("show.code1")) {
				currentShopLogs = currentShop.getReplenishLogs();
			}
			if (userInput.equals("show.code2")) {
				currentShopLogs = currentShop.getRemoveLogs();
			}
			if (userInput.equals("show.code3")) {
				currentShopLogs = currentShop.getBuyLogs();
			}
			if (userInput.equals("show.code4")) {
				currentShopLogs = currentShop.getProductListRequestLogs();
			}
			if (currentShopLogs != null) {
				sb.append("\n>>" + currentShop.getName() + " logfile's content:");
				while (currentShopLogs.hasNext()) {
					sb.append("\n" + currentShopLogs.next());
				}
			}
		}
		return sb;
	}

	/**
	 * Make a Plaza with Shops and Products
	 */
	private static PlazaImpl initialzePlaza() {
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
		// Cheeses
		Product cheese1 = pFactory.makeNewCheese("Tehen", "2016-12-01", 12.6, 10.2);
		Product cheese2 = pFactory.makeNewCheese("Sajt", "2016-10-01", 11.4, 5.5);
		// Soaps
		Product soap1 = pFactory.makeNewSoap("Szappan", 'A');
		Product soap2 = pFactory.makeNewSoap("Soap", 'B');
		// Creating shops and a plaza
		Vector<Shop> shops = new Vector<Shop>();
		shops.add(new Shop("Coop", "Egyenes utca 1.", "K. Jozsi", "C:/Users/Public/Documents"));
		shops.add(new Shop("Spar", "Egyenes utca 1.", "S. Jozsi", "C:/Users/Public/Documents"));
		shops.add(new Shop("VegyesBolt", "Egyenes utca 1.", "V. Jozsi", "C:/Users/Public/Documents"));
		shops.add(new Shop("TheShop", "Egyenes utca 1.", "T. Jozsi", "C:/Users/Public/Documents"));
		PlazaImpl newPlazaObject = new PlazaImpl(shops);
		// Iterate through the shops and add the products to them
		Iterator<Shop> shopIter = newPlazaObject.getAllShops();
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
			currentShop.buyProduct(milk1.getBarcode(), 5);
			currentShop.buyProduct(cheese1.getBarcode(), 5);
			currentShop.buyProduct(soap1.getBarcode(), 5);
			currentShop.removeProduct(milk2.getBarcode());
			currentShop.removeProduct(cheese2.getBarcode());
			currentShop.removeProduct(soap2.getBarcode());
			currentShop.replenishProduct(milk1, 6);
			currentShop.replenishProduct(soap1, 6);
			currentShop.replenishProduct(cheese1, 6);
			currentShop.close();
		}
		newPlazaObject.close();
		return newPlazaObject;
	}
}
