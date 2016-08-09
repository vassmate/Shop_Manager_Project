package shop.product;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Food {
	private long barcode;
	private String company;
	private LocalDate warrant;

	public Food(long barcode, String company, String warrant) {
		this.barcode = barcode;
		this.company = company;
		setWarrant(warrant);
	}

	public long getBarcode() {
		return barcode;
	}

	public String getCompany() {
		return company;
	}

	public LocalDate getWarrant() {
		return warrant;
	}

	private void setWarrant(String warrant) {
		LocalDate date = LocalDate.parse(warrant, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		this.warrant = date;
	}

	public boolean isEatable() {
		LocalDate currentDate = LocalDate.now();
		return this.warrant.compareTo(currentDate) > 0;
	}

	@Override
	public String toString() {
		return "\n_Barcode:" + getBarcode() + "\nCompany:" + getCompany() + "\n_Warrant:" + getWarrant();
	}
}
