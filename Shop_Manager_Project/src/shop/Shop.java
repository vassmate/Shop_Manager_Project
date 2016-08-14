package shop;

import java.util.Hashtable;
import java.util.Iterator;

import shop.behavior.ShopBehaviorImpl;

public class Shop {

	private String name;
	private String address;
	private String owner;
	private Hashtable<Long, ShopRegistration> productStock = new Hashtable<Long, ShopRegistration>();
	private ShopBehaviorImpl shopBehavior = new ShopBehaviorImpl(this);

	public Shop(String name, String address, String owner) {
		this.name = name;
		this.address = address;
		this.owner = owner;
	}

	public Shop(String name, String address, String owner, Hashtable<Long, ShopRegistration> productStock) {
		this.name = name;
		this.address = address;
		this.owner = owner;
		this.productStock = productStock;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getOwner() {
		return owner;
	}

	public Hashtable<Long, ShopRegistration> getProductStock() {
		return productStock;
	}

	public Iterator<Long> getProductsIterator() {
		return shopBehavior.getProductsIterator();
	}

	public boolean isThereProductOnStock(Long barcode) {
		return shopBehavior.isThereProductOnStock(barcode);
	}

	public boolean isThereMilkOnStock() {
		return shopBehavior.isThereMilkOnStock();
	}

	public boolean isThereCheeseOnStock() {
		return shopBehavior.isThereCheeseOnStock();
	}

	public void buyProduct(Long barcode, int quantity) {
		shopBehavior.buyProduct(barcode, quantity);
	}

	public void replenishProduct(Product product, int quantity) {
		shopBehavior.replenishProduct(product, quantity);
	}

	public void addNewProduct(Product product, int quantity, int price) {
		shopBehavior.addNewProduct(product, quantity, price);
	}

	public void removeProduct(long barcode) {
		shopBehavior.removeProduct(barcode);
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
