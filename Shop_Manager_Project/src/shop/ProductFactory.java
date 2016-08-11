package shop;

import java.util.UUID;

import shop.product.Cheese;
import shop.product.Milk;
import shop.product.Soap;
import shop.product.milk.LongLifeMilk;
import shop.product.milk.SemiLongLifeMilk;

public class ProductFactory {

	public LongLifeMilk makeNewLongLifeMilk(String company, String warrant, double capacity, double dripping) {
		LongLifeMilk llMilk = new LongLifeMilk(generateId(), company, warrant, capacity, dripping);
		return llMilk;
	}

	public SemiLongLifeMilk makeNewSemiLongLifeMilk(String company, String warrant, double capacity, double dripping) {
		SemiLongLifeMilk sllMilk = new SemiLongLifeMilk(generateId(), company, warrant, capacity, dripping);
		return sllMilk;
	}

	public LongLifeMilk makeNewSemiSkimmedLLMilk(String company, String warrant, double capacity) {
		LongLifeMilk llMilk = new LongLifeMilk(generateId(), company, warrant, capacity, Milk.SEMI_SKIMMED);
		return llMilk;
	}

	public LongLifeMilk makeNewWholeLLMilk(String company, String warrant, double capacity) {
		LongLifeMilk llMilk = new LongLifeMilk(generateId(), company, warrant, capacity, Milk.WHOLE);
		return llMilk;
	}

	public SemiLongLifeMilk makeNewSemiSkimmedSLLMilk(String company, String warrant, double capacity) {
		SemiLongLifeMilk sllMilk = new SemiLongLifeMilk(generateId(), company, warrant, capacity, Milk.SEMI_SKIMMED);
		return sllMilk;
	}

	public SemiLongLifeMilk makeNewWholeSLLMilk(String company, String warrant, double capacity) {
		SemiLongLifeMilk sllMilk = new SemiLongLifeMilk(generateId(), company, warrant, capacity, Milk.WHOLE);
		return sllMilk;
	}

	public LongLifeMilk makeNewLiterSemiSkimmedLLMilk(String company, String warrant) {
		LongLifeMilk llMilk = new LongLifeMilk(generateId(), company, warrant, Milk.LITER, Milk.SEMI_SKIMMED);
		return llMilk;
	}

	public LongLifeMilk makeNewLiterWholeLLMilk(String company, String warrant) {
		LongLifeMilk llMilk = new LongLifeMilk(generateId(), company, warrant, Milk.LITER, Milk.WHOLE);
		return llMilk;
	}

	public SemiLongLifeMilk makeNewLiterSemiSkimmedSLLMilk(String company, String warrant) {
		SemiLongLifeMilk sllMilk = new SemiLongLifeMilk(generateId(), company, warrant, Milk.LITER, Milk.SEMI_SKIMMED);
		return sllMilk;
	}

	public SemiLongLifeMilk makeNewLiterWholeSLLMilk(String company, String warrant) {
		SemiLongLifeMilk sllMilk = new SemiLongLifeMilk(generateId(), company, warrant, Milk.LITER, Milk.WHOLE);
		return sllMilk;
	}

	public Cheese makeNewCheese(String company, String warrant, double weight, double dripping) {
		Cheese cheese = new Cheese(generateId(), company, warrant, weight, dripping);
		return cheese;
	}

	public Soap makeNewSoap(String company, char detergency) {
		Soap soap = new Soap(generateId(), company, detergency);
		return soap;
	}

	private static long generateId() {
		UUID id = UUID.randomUUID();
		return Math.abs(id.getMostSignificantBits());
	}
}
