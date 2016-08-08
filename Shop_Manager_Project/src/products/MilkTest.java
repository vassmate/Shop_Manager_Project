package products;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import org.junit.Test;

public class MilkTest {

	@Test
	public void testDrinkableForTrue() {
		Milk milk = new Milk(generateId(), "Falusi", "2016-11-20", 1.5, 20.5);
		assertEquals(true, milk.isDrinkable());
	}

	@Test
	public void testDrinkableForFalse() {
		Milk milk = new Milk(generateId(), "Mizo", "2016-06-20", 1.5, 10.5);
		assertEquals(false, milk.isDrinkable());
	}

	private static long generateId() {
		UUID id = UUID.randomUUID();
		return Math.abs(id.getMostSignificantBits());
	}
}
