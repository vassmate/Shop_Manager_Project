package shop;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import shop.behavior.IShopBehavior;
import shop.exception.NoMoreProductException;
import shop.exception.NoSuchProductException;
import shop.product.Cheese;
import shop.product.milk.LongLifeMilk;
import shop.product.milk.SemiLongLifeMilk;

public class Shop implements IShopBehavior {

	private String name;
	private String address;
	private String owner;
	private Hashtable<Long, ShopRegistration> productStock = new Hashtable<Long, ShopRegistration>();

	public Shop(String name, String address, String owner, Hashtable<Long, ShopRegistration> productStock) {
		this.name = name;
		this.address = address;
		this.owner = owner;
		this.productStock = productStock;
	}

	public Shop(String name, String address, String owner) {
		this.name = name;
		this.address = address;
		this.owner = owner;
	}

	public Hashtable<Long, ShopRegistration> getProductStock() {
		return productStock;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public String getOwner() {
		return owner;
	}

	@Override
	public Iterator<Long> getProductsIterator() {
		Set<Long> pKeys = getProductStock().keySet();
		Iterator<Long> pIterator = pKeys.iterator();
		return pIterator;
	}

	public boolean isThereProductOnStock(Long barcode) {
		if (getProductStock().size() == 0 || !getProductStock().containsKey(barcode)) {
			return false;
		}
		return true;
	}

	public boolean isThereMilkOnStock() {
		ProductIterator pIter = new ProductIterator(getProductsIterator());
		while (pIter.hasNext()) {
			Product currentProduct = pIter.next().getProduct();
			if (currentProduct instanceof SemiLongLifeMilk || currentProduct instanceof LongLifeMilk) {
				return true;
			}
		}
		return false;
	}

	public boolean isThereCheeseOnStock() {
		ProductIterator pIter = new ProductIterator(getProductsIterator());
		while (pIter.hasNext()) {
			Product currentProduct = pIter.next().getProduct();
			if (currentProduct instanceof Cheese) {
				return true;
			}
		}
		return false;
	}

	public void buyProduct(Long barcode, int quantity) {
		try {
			if (isThereProductOnStock(barcode)) {
				if (getProductStock().get(barcode).getQuantity() >= quantity) {
					getProductStock().get(barcode).decreaseQuantity(quantity);
				} else {
					throw new NoMoreProductException(getProductStock().get(barcode).getProduct());
				}
			} else {
				throw new NoSuchProductException();
			}
		} catch (ShopException ex) {
			ex.printStackTrace();
		}
	}

	public void replenishProduct(Product product, int quantity) {
		try {
			if (isThereProductOnStock(product.getBarcode())) {
				getProductStock().get(product.getBarcode()).increaseQuantity(quantity);
			} else {
				throw new NoSuchProductException(product);
			}
		} catch (ShopException ex) {
			ex.printStackTrace();
		}
	}

	public void addNewProduct(Product product, int quantity, int price) {
		getProductStock().put(product.getBarcode(), new ShopRegistration(product, quantity, price));
	}

	public void removeProduct(long barcode) {
		try {
			if (isThereProductOnStock(barcode)) {
				getProductStock().remove(barcode);
			} else {
				throw new NoSuchProductException();
			}
		} catch (ShopException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "\n\tSHOP_NAME: " + getName() + "\n\tSHOP_ADDRESS: " + getAddress() + "\n\tOWNER: " + getOwner();
	}

	public class ShopRegistration {

		private Product product;
		private int quantity;
		private int price;

		public ShopRegistration(Product product, int quantity, int price) {
			this.product = product;
			this.quantity = quantity;
			this.price = price;
		}

		public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		public void increaseQuantity(int increaseBy) {
			this.quantity += increaseBy;
		}

		public void decreaseQuantity(int decreaseBy) {
			this.quantity -= decreaseBy;
		}

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}

		@Override
		public String toString() {
			return "\n\t\tPRODUCT:" + getProduct() + "\n\t\tQUANTITY:" + getQuantity() + "\n\t\tPRICE:" + getPrice();
		}
	}

	public class ProductIterator implements Iterator<ShopRegistration> {

		private Iterator<Long> pIterator;

		public ProductIterator(Iterator<Long> pIterator) {
			this.pIterator = pIterator;
		}

		public Iterator<Long> getPIterator() {
			return pIterator;
		}

		@Override
		public boolean hasNext() {
			if (getPIterator().hasNext()) {
				return true;
			}
			return false;
		}

		@Override
		public ShopRegistration next() {
			return getProductStock().get(getPIterator().next());
		}

		@Override
		public void remove() {
			getProductStock().remove(getPIterator().next());
		}
	}
}
