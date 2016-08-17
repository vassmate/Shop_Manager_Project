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
		return  super.toString() + "_Type:Soap" + "_Detergency:" + getDetergency();
	}
}
