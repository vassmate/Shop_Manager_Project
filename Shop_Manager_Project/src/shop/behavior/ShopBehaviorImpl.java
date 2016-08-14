package shop.behavior;

import java.util.Iterator;
import java.util.Set;

import shop.Product;
import shop.Shop;
import shop.Shop.ProductIterator;
import shop.ShopException;
import shop.exception.NoMoreProductException;
import shop.exception.NoSuchProductException;
import shop.product.Cheese;
import shop.product.milk.LongLifeMilk;
import shop.product.milk.SemiLongLifeMilk;

public class ShopBehaviorImpl implements IShopBehavior {

	private Shop shop;

	public ShopBehaviorImpl(Shop shop) {
		this.shop = shop;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	@Override
	public Iterator<Long> getProductsIterator() {
		Set<Long> pKeys = getShop().getProductStock().keySet();
		Iterator<Long> pIterator = pKeys.iterator();
		return pIterator;
	}

	@Override
	public boolean isThereProductOnStock(Long barcode) {
		if (getShop().getProductStock().size() == 0 || !getShop().getProductStock().containsKey(barcode)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isThereMilkOnStock() {
		ProductIterator pIter = getShop().new ProductIterator(getProductsIterator());
		while (pIter.hasNext()) {
			Product currentProduct = pIter.next().getProduct();
			if (currentProduct instanceof SemiLongLifeMilk || currentProduct instanceof LongLifeMilk) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isThereCheeseOnStock() {
		ProductIterator pIter = getShop().new ProductIterator(getProductsIterator());
		while (pIter.hasNext()) {
			Product currentProduct = pIter.next().getProduct();
			if (currentProduct instanceof Cheese) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void buyProduct(Long barcode, int quantity) {
		try {
			if (isThereProductOnStock(barcode)) {
				if (getShop().getProductStock().get(barcode).getQuantity() >= quantity) {
					getShop().getProductStock().get(barcode).decreaseQuantity(quantity);
				} else {
					throw new NoMoreProductException(getShop().getProductStock().get(barcode).getProduct());
				}
			} else {
				throw new NoSuchProductException();
			}
		} catch (ShopException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void replenishProduct(Product product, int quantity) {
		try {
			if (isThereProductOnStock(product.getBarcode())) {
				getShop().getProductStock().get(product.getBarcode()).increaseQuantity(quantity);
			} else {
				throw new NoSuchProductException(product);
			}
		} catch (ShopException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void addNewProduct(Product product, int quantity, int price) {
		getShop().getProductStock().put(product.getBarcode(), getShop().new ShopRegistration(product, quantity, price));
	}

	@Override
	public void removeProduct(long barcode) {
		try {
			if (isThereProductOnStock(barcode)) {
				getShop().getProductStock().remove(barcode);
			} else {
				throw new NoSuchProductException();
			}
		} catch (ShopException ex) {
			ex.printStackTrace();
		}
	}
}
