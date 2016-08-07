package shop;

import java.util.Vector;

import products.Milk;

public class Shop {

	private String name;
	private String address;
	private String owner;
	private Vector<Milk> milkBar;

	public Shop(String name, String address, String owner, Vector<Milk> milkBar) {
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

	public Vector<Milk> getMilkBar() {
		return milkBar;
	}

	private void setMilkBar(Vector<Milk> milkBar) {
		this.milkBar = milkBar;
	}

	public boolean isThereMilk() {
		if (getMilkBar() == null || getMilkBar().size() == 0) {
			return false;
		}
		return true;
	}

	public Milk buyMilk(Milk m) {

		if (isThereMilk()) {
			@SuppressWarnings("unchecked")
			Vector<Milk> currentMilkBar = (Vector<Milk>) getMilkBar().clone();

			if (currentMilkBar.contains(m)) {
				getMilkBar().remove(m);
				return currentMilkBar.get(currentMilkBar.indexOf(m));
			}
			return getMilkBar().get(0);
		}
		setMilkBar(new Vector<Milk>());
		return new Milk("No_Milk", 0, "1999-01-01", 0.0, 0.0);
	}

	public void replenishMilk(Milk m) {
		if (getMilkBar() != null && isThereMilk()) {
			if (getMilkBar().capacity() - getMilkBar().size() > 0) {
				getMilkBar().add(m);
			} else {
				getMilkBar().addElement(m);
			}
		} else {
			setMilkBar(new Vector<Milk>());
			getMilkBar().add(m);
		}
	}

	@Override
	public String toString() {
		return "Name: " + getName() + " Address: " + getAddress() + " Owner: " + getOwner();
	}
}
