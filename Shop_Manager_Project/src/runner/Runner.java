package runner;

import products.Milk;
import shop.Shop;

public class Runner {

	public static void main(String[] args) {

		Milk milk1 = new Milk("Falusi", 299, "2016-11-20", 1.5, 20.5);
		Milk milk2 = new Milk("Riska", 240, "2016-05-10", 1.5, 19.5);
		Milk milk3 = new Milk("Mizo", 320, "2016-06-15", 1.5, 5.5);
		Milk milk4 = new Milk("Spar", 199, "2016-08-20", 1.5, 10);
		Milk milk5 = new Milk("Milk", 209, "2016-12-20", 1.5, 2.5);
		Milk milk6 = new Milk("Tej", 209, "2016-12-20", 1.5, 2.5);

		Milk[] milkArray = { milk1, milk2, milk3, milk4, milk5 };

		Shop milkShop = new Shop("Milk Shop", "Egyenes utca 1.", "Kovacs Jozsi", milkArray);

		printMilkShop(milkShop);
		milkShop.buyMilk(milk4);
		printMilkShop(milkShop);
		milkShop.buyMilk(milk1);
		printMilkShop(milkShop);
		milkShop.buyMilk(milk6);
		printMilkShop(milkShop);
	}

	private static void printMilks(Milk[] milks) {
		for (Milk milk : milks) {
			System.out.println("\t\t" + milk);
		}
	}

	private static void printMilkShop(Shop milkShop) {
		System.out.println("Milk Shop:\n\t" + milkShop + "\n\tMilk bar:");
		printMilks(milkShop.getMilkBar());
	}
}
