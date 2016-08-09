package runner;

import java.util.Hashtable;

import shop.ProductFactory;
import shop.Shop;
import shop.product.Cheese;
import shop.product.Milk;

public class Runner {

	public static void main(String[] args) {
		ProductFactory mFactory = new ProductFactory();
		Shop mShop = new Shop("Milk Shop", "Egyenes utca 1.", "Kovacs Jozsi");

		Milk milk1 = mFactory.makeNewLongLifeMilk("Falusi", "2016-11-20", 1000, 2.5);
		Milk milk2 = mFactory.makeNewLongLifeMilk("Riska", "2016-05-10", 500, 1.5);
		Milk milk3 = mFactory.makeNewLongLifeMilk("Mizo", "2016-06-15", 600, 5.5);
		Milk milk4 = mFactory.makeNewSemiLongLifeMilk("Spar", "2016-08-20", 750, 2.2);
		Milk milk5 = mFactory.makeNewSemiLongLifeMilk("Milk", "2016-12-20", 1500, 2.5);
		Milk milk6 = mFactory.makeNewSemiLongLifeMilk("Tej", "2016-12-20", 230, 2.5);
		Cheese cheese1 = mFactory.makeNewCheese("Tehen", "2016-12-01", 12.6, 10.2);
		Cheese cheese2 = mFactory.makeNewCheese("Sajt", "2016-10-01", 11.4, 5.5);

		mShop.addNewFood(milk1, 25, 250);
		mShop.addNewFood(milk2, 20, 300);
		mShop.addNewFood(milk3, 13, 150);
		mShop.addNewFood(milk4, 11, 200);
		mShop.addNewFood(milk5, 40, 120);
		mShop.addNewFood(milk6, 22, 410);
		mShop.addNewFood(cheese1, 20, 6500);
		mShop.addNewFood(cheese2, 20, 7500);

		printMilkShop(mShop);
	}

	private static void printMilks(Hashtable<Long, Shop.ShopRegistration> milks) {
		for (Long barcode : milks.keySet()) {
			System.out.println(milks.get(barcode));
		}
	}

	private static void printMilkShop(Shop milkShop) {
		System.out.println("SHOP:\n\t" + milkShop + "\n\tPRODUCTS:");
		printMilks(milkShop.getFoodBar());
	}
}
