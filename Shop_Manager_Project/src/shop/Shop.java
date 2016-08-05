package shop;

import products.Milk;

public class Shop {

	private String name;
	private String address;
	private String owner;
	private Milk[] milkBar;

	public Shop(String name, String address, String owner, Milk[] milkBar) {
		super();
		this.name = name;
		this.address = address;
		this.owner = owner;
		this.milkBar = milkBar;
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

	public Milk[] getMilkBar() {
		return milkBar;
	}

	public boolean isThereMilk() {
		if (this.milkBar.length == 0) {
			return false;
		}
		return true;
	}

	public Milk buyMilk(Milk m) {

		// Make a copy from the milkBar to safely modify and iterate over
		Milk[] currentMilkBar = this.milkBar.clone();

		// Find the sought Milk in the array and make a new array without it
		for (Milk milk : currentMilkBar) {
			if (milk.equals(m)) {
				this.milkBar = removeMilk(currentMilkBar, m);
				return milk;
			}
		}

		// If the sought Milk was not found than we just return with the first
		// one we find and make a new array without it
		this.milkBar = removeMilk(currentMilkBar, currentMilkBar[0]);
		return currentMilkBar[0];
	}

	private static Milk[] removeMilk(Milk[] currentMB, Milk milkToExclude) {
		Milk[] changedMB = new Milk[currentMB.length - 1];

		for (int i = 0; i < changedMB.length; i++) {

			// Check if the current milk is the bought one
			// If it is than we just add the next milk so we exclude it from the
			// new array
			if (currentMB[i].equals(milkToExclude)) {
				changedMB[i] = currentMB[i + 1];
			} else {
				changedMB[i] = currentMB[i];
			}

			// Check if the previous element is already in the list or not
			// If it is than we add the next one so there won't be any
			// duplicates
			if (i - 1 >= 0) {
				if (currentMB[i].equals(changedMB[i - 1])) {
					changedMB[i] = currentMB[i + 1];
				}
			}
		}

		return changedMB;
	}

	@Override
	public String toString() {
		return "Name: " + getName() + " Address: " + getAddress() + " Owner: " + getOwner();
	}
}
