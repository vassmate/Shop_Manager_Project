package shop.test;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import org.junit.Test;

import shop.product.Milk;
import shop.product.milk.LongLifeMilk;

public class MilkTest {

	@Test
	public void testDrinkableForTrue() {
		Milk milk = new LongLifeMilk(generateId(), "Falusi", "2016-11-20", 1500, 2.5);
		assertEquals(true, milk.isEatable());
	}

	@Test
	public void testDrinkableForFalse() {
		Milk milk = new LongLifeMilk(generateId(), "Mizo", "2016-06-20", 1500, 1.5);
		assertEquals(false, milk.isEatable());
	}

	private static long generateId() {
		UUID id = UUID.randomUUID();
		return Math.abs(id.getMostSignificantBits());
	}
}
