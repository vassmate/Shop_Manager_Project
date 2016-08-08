package shop;

import java.util.Hashtable;

import products.Milk;

public class Shop {

	private String name;
	private String address;
	private String owner;
	private Hashtable<Long, ShopRegistration> milkBar;

	public Shop(String name, String address, String owner, Hashtable<Long, ShopRegistration> milkBar) {
		this.name = name;
		this.address = address;
		this.owner = owner;
		this.milkBar = milkBar;
	}

	public Shop(String name, String address, String owner) {
		this.name = name;
		this.address = address;
		this.owner = owner;
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

	public Hashtable<Long, ShopRegistration> getMilkBar() {
		return milkBar;
	}

	private void setMilkBar(Hashtable<Long, ShopRegistration> milkBar) {
		this.milkBar = milkBar;
	}

	public boolean isThereMilk() {
		if (getMilkBar() == null || getMilkBar().size() == 0) {
			return false;
		}
		return true;
	}

	public Milk buyMilk(Long barcode) {
		if (isThereMilk()) {
			if (getMilkBar().containsKey(barcode)) {
				if (getMilkBar().get(barcode).getQuantity() > 0) {
					getMilkBar().get(barcode).decreaseQuantity();
					return getMilkBar().get(barcode).getMilk();
				}
			}
		}
		return null;
	}

	public void replenishMilk(Milk milk, int price) {
		if (isThereMilk()) {
			if (getMilkBar().containsKey(milk.getBarcode())) {
				getMilkBar().get(milk.getBarcode()).increaseQuantity();
			} else {
				getMilkBar().put(milk.getBarcode(), new ShopRegistration(milk, 1, price));
			}
		} else {
			setMilkBar(new Hashtable<Long, ShopRegistration>());
			getMilkBar().put(milk.getBarcode(), new ShopRegistration(milk, 1, price));
		}
	}

	@Override
	public String toString() {
		return "Name: " + getName() + " Address: " + getAddress() + " Owner: " + getOwner();
	}

	public class ShopRegistration {

		private Milk milk;
		private int quantity;
		private int price;

		public ShopRegistration(Milk milk, int quantity, int price) {
			this.milk = milk;
			this.quantity = quantity;
			this.price = price;
		}

		public Milk getMilk() {
			return milk;
		}

		public void setMilk(Milk milk) {
			this.milk = milk;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		public void increaseQuantity() {
			this.quantity++;
		}

		public void decreaseQuantity() {
			this.quantity--;
		}

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}

		@Override
		public String toString() {
			return "\n\t\tMILK:" + getMilk() + "\n\t\tQUANTITY:" + getQuantity() + "\n\t\tPRICE:" + getPrice();
		}

	}
}
