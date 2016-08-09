package shop.product;

public abstract class Milk extends Food {
	private final int LITER = 1000;
	private final int HALF_LITER = 500;
	private final int CUP = 250;
	private final double WHOLE = 3.8;
	private final double SEMI_SKIMMED = 2.8;

	private double capacity;
	private double dripping;

	public Milk(long barcode, String company, String warrant, double capacity, double dripping) {
		super(barcode, company, warrant);
		setCapacity(capacity);
		setDripping(dripping);
	}

	public double getCapacity() {
		return capacity;
	}

	private void setCapacity(double capacity) {
		if (capacity > 700) {
			this.capacity = LITER;
		} else if (capacity < 700 && capacity > 400) {
			this.capacity = HALF_LITER;
		} else {
			this.capacity = CUP;
		}
	}

	public double getDripping() {
		return dripping;
	}

	private void setDripping(double dripping) {
		if (dripping > 2.8) {
			this.dripping = WHOLE;
		} else {
			this.dripping = SEMI_SKIMMED;
		}
	}

	@Override
	public String toString() {
		return "\n\t\t\t_Type:Milk" + "\n\t\t\t_Barcode:" + getBarcode() + "\n\t\t\t_Company:" + getCompany()
				+ "\n\t\t\t_Warrant:" + getWarrant() + "\n\t\t\t_Capacity:" + getCapacity() + "\n\t\t\t_Dripping:"
				+ getDripping() + "\n\t\t\t_Drinkable:" + isEatable();
	}
}
