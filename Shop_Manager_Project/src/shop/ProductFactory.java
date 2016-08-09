package shop;

import java.util.UUID;

import shop.product.Cheese;
import shop.product.milk.LongLifeMilk;
import shop.product.milk.SemiLongLifeMilk;

public class ProductFactory {

	public LongLifeMilk makeNewLongLifeMilk(String company, String warrant, double capacity, double dripping) {
		LongLifeMilk llMilk = new LongLifeMilk(generateId(), company, warrant, capacity, dripping);
		return llMilk;
	}

	public SemiLongLifeMilk makeNewSemiLongLifeMilk(String company, String warrant, double capacity, double dripping) {
		SemiLongLifeMilk slMilk = new SemiLongLifeMilk(generateId(), company, warrant, capacity, dripping);
		return slMilk;
	}

	public Cheese makeNewCheese(String company, String warrant, double weight, double dripping) {
		Cheese cheese = new Cheese(generateId(), company, warrant, weight, dripping);
		return cheese;
	}

	private static long generateId() {
		UUID id = UUID.randomUUID();
		return Math.abs(id.getMostSignificantBits());
	}
}
