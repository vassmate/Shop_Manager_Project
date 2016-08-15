package shop.behavior;

import java.util.Iterator;
import java.util.Set;

import shop.Product;
import shop.Shop;
import shop.Shop.ProductIterator;
import shop.Shop.ShopRegistration;
import shop.ShopException;
import shop.exception.NoMoreProductException;
import shop.exception.NoSuchProductException;
import shop.exception.ShopClosedException;
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
	public void open() {
		if (!getShop().isOpen()) {
			getShop().setOpen(true);
		}
	}

	@Override
	public void close() {
		if (getShop().isOpen()) {
			getShop().setOpen(false);
		}
	}

	@Override
	public Iterator<Long> getProductsIterator() {
		try {
			if (getShop().isOpen()) {
				Set<Long> pKeys = getShop().getProductStock().keySet();
				Iterator<Long> pIterator = pKeys.iterator();
				return pIterator;
			} else {
				throw new ShopClosedException();
			}
		} catch (ShopException ex) {
			System.out.println(ex);
			return null;
		}
	}

	@Override
	public boolean isThereProductOnStock(Long barcode) {
		try {
			if (getShop().isOpen()) {
				if (getShop().getProductStock().size() == 0 || !getShop().getProductStock().containsKey(barcode)) {
					return false;
				}
				return true;
			} else {
				throw new ShopClosedException();
			}
		} catch (ShopException ex) {
			System.out.println(ex);
			return false;
		}
	}

	@Override
	public boolean isThereMilkOnStock() {
		try {
			if (getShop().isOpen()) {
				ProductIterator pIter = getShop().new ProductIterator(getProductsIterator());
				while (pIter.hasNext()) {
					Product currentIterProduct = (Product) pIter.next();
					ShopRegistration currentShopReg = getShop().getProductStock().get(currentIterProduct.getBarcode());
					Product currentProduct = currentShopReg.getProduct();
					if (currentProduct instanceof SemiLongLifeMilk || currentProduct instanceof LongLifeMilk) {
						return true;
					}
				}
				return false;
			} else {
				throw new ShopClosedException();
			}
		} catch (ShopException ex) {
			System.out.println(ex);
			return false;
		}
	}

	@Override
	public boolean isThereCheeseOnStock() {
		try {
			if (getShop().isOpen()) {
				ProductIterator pIter = getShop().new ProductIterator(getProductsIterator());
				while (pIter.hasNext()) {
					Product currentIterProduct = (Product) pIter.next();
					ShopRegistration currentShopReg = getShop().getProductStock().get(currentIterProduct.getBarcode());
					Product currentProduct = currentShopReg.getProduct();
					if (currentProduct instanceof Cheese) {
						return true;
					}
				}
				return false;
			} else {
				throw new ShopClosedException();
			}
		} catch (ShopException ex) {
			System.out.println(ex);
			return false;
		}
	}

	@Override
	public void buyProduct(Long barcode, int quantity) {
		try {
			if (getShop().isOpen()) {
				if (isThereProductOnStock(barcode)) {
					if (getShop().getProductStock().get(barcode).getQuantity() >= quantity) {
						getShop().getProductStock().get(barcode).decreaseQuantity(quantity);
					} else {
						throw new NoMoreProductException(getShop().getProductStock().get(barcode).getProduct());
					}
				} else {
					throw new NoSuchProductException();
				}
			} else {
				throw new ShopClosedException();
			}
		} catch (ShopException ex) {
			System.out.println(ex);
		}
	}

	@Override
	public void replenishProduct(Product product, int quantity) {
		try {
			if (getShop().isOpen()) {
				if (isThereProductOnStock(product.getBarcode())) {
					getShop().getProductStock().get(product.getBarcode()).increaseQuantity(quantity);
				} else {
					throw new NoSuchProductException(product);
				}
			} else {
				throw new ShopClosedException();
			}
		} catch (ShopException ex) {
			System.out.println(ex);
		}
	}

	@Override
	public void addNewProduct(Product product, int quantity, int price) {
		try {
			if (getShop().isOpen()) {
				getShop().getProductStock().put(product.getBarcode(),
						getShop().new ShopRegistration(product, quantity, price));
			} else {
				throw new ShopClosedException();
			}
		} catch (ShopException ex) {
			System.out.println(ex);
		}
	}

	@Override
	public void removeProduct(long barcode) {
		try {
			if (getShop().isOpen()) {
				if (isThereProductOnStock(barcode)) {
					getShop().getProductStock().remove(barcode);
				} else {
					throw new NoSuchProductException();
				}
			} else {
				throw new ShopClosedException();
			}
		} catch (ShopException ex) {
			System.out.println(ex);
		}
	}
	
}
