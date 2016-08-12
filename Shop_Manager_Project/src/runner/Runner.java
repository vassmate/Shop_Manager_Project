package runner;

import shop.Product;
import shop.ProductFactory;
import shop.Shop;
import shop.Shop.ProductIterator;
import shop.product.Milk;

public class Runner {

	public static void main(String[] args) {
		ProductFactory pFactory = new ProductFactory();
		Shop fShop = new Shop("Milk Shop", "Egyenes utca 1.", "Kovacs Jozsi");

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

		// Cheese
		Product cheese1 = pFactory.makeNewCheese("Tehen", "2016-12-01", 12.6, 10.2);
		Product cheese2 = pFactory.makeNewCheese("Sajt", "2016-10-01", 11.4, 5.5);

		// Soap
		Product soap1 = pFactory.makeNewSoap("Szappan", 'A');
		Product soap2 = pFactory.makeNewSoap("Soap", 'B');

		// Adding stuff to the stock
		fShop.addNewProduct(milk1, 25, 250);
		fShop.addNewProduct(milk2, 20, 300);
		fShop.addNewProduct(milk3, 13, 150);
		fShop.addNewProduct(milk4, 11, 200);
		fShop.addNewProduct(milk5, 40, 120);
		fShop.addNewProduct(milk6, 22, 410);
		fShop.addNewProduct(milk7, 12, 320);
		fShop.addNewProduct(milk8, 32, 510);
		fShop.addNewProduct(milk9, 44, 310);
		fShop.addNewProduct(milk10, 36, 210);
		fShop.addNewProduct(cheese1, 20, 620);
		fShop.addNewProduct(cheese2, 20, 750);
		fShop.addNewProduct(soap1, 20, 650);
		fShop.addNewProduct(soap2, 20, 450);

		// Print all data
		printShop(fShop);
	}

	private static void printShop(Shop shop) {
		ProductIterator pIter = shop.new ProductIterator(shop.getProductsIterator());
		System.out.println("SHOP:" + shop + "\n\tPRODUCTS:");
		while (pIter.hasNext()) {
			System.out.println(pIter.next());
		}
	}
}
