package shop.product;

public abstract class Milk extends Food {
	public static final int LITER = 1000;
	public static final int HALF_LITER = 500;
	public static final int CUP = 250;
	public static final double WHOLE = 3.8;
	public static final double SEMI_SKIMMED = 2.8;

	private double capacity;
	private double dripping;

	public Milk(long barcode, String company, String warrant, double capacity, double dripping) {
		super(barcode, company, warrant);
		this.capacity = capacity;
		this.dripping = dripping;
	}

	public double getCapacity() {
		return capacity;
	}

	public double getDripping() {
		return dripping;
	}

	@Override
	public String toString() {
		return  super.toString() + "_Type:Milk" + "_Capacity:" + getCapacity() + "_Dripping:"
				+ getDripping();
	}
}
