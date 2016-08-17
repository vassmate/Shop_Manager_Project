package shop;

public class Product {
	private long barcode;
	private String company;

	public Product(long barcode, String company) {
		this.barcode = barcode;
		this.company = company;
	}

	public long getBarcode() {
		return barcode;
	}

	public String getCompany() {
		return company;
	}

	@Override
	public String toString() {
		return "\n\t\t_Barcode:" + getBarcode() + "_Company:" + getCompany();
	}
}
