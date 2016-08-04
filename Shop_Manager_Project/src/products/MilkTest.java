package products;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MilkTest {

	@Test
	public void testDrinkableForTrue() {
		Milk milk = new Milk("Falusi", 299, "2016-11-20", 1.5, 20.5);
		assertEquals(true, milk.isDrinkable());
	}

	@Test
	public void testDrinkableForFalse() {
		Milk milk = new Milk("Mizo", 199, "2016-06-20", 1.5, 10.5);
		assertEquals(false, milk.isDrinkable());
	}
}
