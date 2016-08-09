package shop;

import java.util.Hashtable;

import shop.product.Food;

public class Shop {

	private String name;
	private String address;
	private String owner;
	private Hashtable<Long, ShopRegistration> foodBar = new Hashtable<Long, ShopRegistration>();

	public Shop(String name, String address, String owner, Hashtable<Long, ShopRegistration> foodBar) {
		this.name = name;
		this.address = address;
		this.owner = owner;
		this.foodBar = foodBar;
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

	public Hashtable<Long, ShopRegistration> getFoodBar() {
		return foodBar;
	}

	public boolean isThereFood(Long barcode) {
		if (getFoodBar().size() == 0 || !getFoodBar().containsKey(barcode)
				|| getFoodBar().get(barcode).getQuantity() == 0) {
			return false;
		}
		return true;
	}

	public void buyFood(Long barcode, int quantity) {
		if (isThereFood(barcode)) {
			if (getFoodBar().get(barcode).getQuantity() >= quantity) {
				getFoodBar().get(barcode).decreaseQuantity(quantity);
			}
		}
	}

	public void replenishFood(Food food, int quantity) {
		if (isThereFood(food.getBarcode())) {
			getFoodBar().get(food.getBarcode()).increaseQuantity(quantity);
		}
	}

	public void addNewFood(Food food, int quantity, int price) {
		getFoodBar().put(food.getBarcode(), new ShopRegistration(food, quantity, price));
	}

	@Override
	public String toString() {
		return "SHOP_NAME: " + getName() + "\n\tSHOP_ADDRESS: " + getAddress() + "\n\tOWNER: " + getOwner();
	}

	public class ShopRegistration {

		private Food food;
		private int quantity;
		private int price;

		public ShopRegistration(Food food, int quantity, int price) {
			this.food = food;
			this.quantity = quantity;
			this.price = price;
		}

		public Food getFood() {
			return food;
		}

		public void setFood(Food food) {
			this.food = food;
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
			return "\n\t\tPRODUCT:" + getFood() + "\n\t\tQUANTITY:" + getQuantity() + "\n\t\tPRICE:" + getPrice();
		}

	}
}
