package shop.product;

import shop.Product;

public class Soap extends Product {
	private char detergency;

	public Soap(long barcode, String company, char detergency) {
		super(barcode, company);

		this.detergency = detergency;
	}

	public char getDetergency() {
		return detergency;
	}

	@Override
	public String toString() {
		return "\n\t\t\t_Type:Soap" + super.toString() + "\n\t\t\t_Detergency:" + getDetergency();
	}
}
