package shop;

import java.util.UUID;

import shop.product.milk.LongLifeMilk;
import shop.product.milk.SemiLongLifeMilk;

public class MilkFactory {

	public LongLifeMilk makeNewLongLifeMilk(String company, String warrant, double capacity, double dripping) {
		LongLifeMilk llMilk = new LongLifeMilk(generateId(), company, warrant, capacity, dripping);
		return llMilk;
	}

	public SemiLongLifeMilk makeNewSemiLongLifeMilk(String company, String warrant, double capacity, double dripping) {
		SemiLongLifeMilk llMilk = new SemiLongLifeMilk(generateId(), company, warrant, capacity, dripping);
		return llMilk;
	}

	private static long generateId() {
		UUID id = UUID.randomUUID();
		return Math.abs(id.getMostSignificantBits());
	}
}
