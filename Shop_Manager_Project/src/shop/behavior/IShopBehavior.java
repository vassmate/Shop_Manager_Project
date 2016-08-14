package shop.behavior;

import java.util.Iterator;

import shop.Product;

public interface IShopBehavior {

	public Iterator<Long> getProductsIterator();

	public boolean isThereProductOnStock(Long barcode);

	public boolean isThereMilkOnStock();

	public boolean isThereCheeseOnStock();

	public void buyProduct(Long barcode, int quantity);

	public void replenishProduct(Product product, int quantity);

	public void addNewProduct(Product product, int quantity, int price);

	public void removeProduct(long barcode);

}
