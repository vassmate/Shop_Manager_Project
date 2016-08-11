package shop;

public abstract class Product {
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
		return "\n\t\t\t_Barcode:" + getBarcode() + "\n\t\t\t_Company:" + getCompany();
	}
}
