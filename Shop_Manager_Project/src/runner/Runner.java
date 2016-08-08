package runner;

import java.util.Hashtable;
import java.util.UUID;

import products.Milk;
import shop.Shop;

public class Runner {

	public static void main(String[] args) {

		Milk milk1 = new Milk(generateId(), "Falusi", "2016-11-20", 1000, 2.5);
		Milk milk2 = new Milk(generateId(), "Riska", "2016-05-10", 500, 19.5);
		Milk milk3 = new Milk(generateId(), "Mizo", "2016-06-15", 600, 5.5);
		Milk milk4 = new Milk(generateId(), "Spar", "2016-08-20", 750, 10);
		Milk milk5 = new Milk(generateId(), "Milk", "2016-12-20", 1500, 2.5);
		Milk milk6 = new Milk(generateId(), "Tej", "2016-12-20", 230, 2.5);

		Shop milkShop = new Shop("Milk Shop", "Egyenes utca 1.", "Kovacs Jozsi");

		milkShop.replenishMilk(milk1, 299);
		milkShop.replenishMilk(milk2, 250);
		milkShop.replenishMilk(milk3, 135);
		milkShop.replenishMilk(milk4, 110);
		milkShop.replenishMilk(milk5, 400);
		milkShop.replenishMilk(milk6, 225);

		printMilkShop(milkShop);
	}

	private static void printMilks(Hashtable<Long, Shop.ShopRegistration> milks) {
		for (Long barcode : milks.keySet()) {
			System.out.println(milks.get(barcode));
		}
	}

	private static void printMilkShop(Shop milkShop) {
		System.out.println("Milk Shop:\n\t" + milkShop + "\n\tMilk bar:");
		printMilks(milkShop.getMilkBar());
	}

	private static long generateId() {
		UUID id = UUID.randomUUID();
		return Math.abs(id.getMostSignificantBits());
	}
}
