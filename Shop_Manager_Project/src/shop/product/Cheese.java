package shop.product;

public class Cheese extends Food {

	private double weight;
	private double dripping;

	public Cheese(long barcode, String company, String warrant, double weight, double dripping) {
		super(barcode, company, warrant);
		this.weight = weight;
		this.dripping = dripping;
	}

	public double getWeight() {
		return weight;
	}

	public double getDripping() {
		return dripping;
	}

	@Override
	public String toString() {
		return "\n\t\t\t_Type:Cheese" + super.toString() + "\n\t\t\t_Weight:" + getWeight() + "\n\t\t\t_Dripping:"
				+ getDripping();
	}
}
