package shop.product;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import shop.Product;

public abstract class Food extends Product {
	private LocalDate warrant;

	public Food(long barcode, String company, String warrant) {
		super(barcode, company);
		setWarrant(warrant);
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
		return super.toString() + "\n\t\t\t_Warrant:" + getWarrant() + "\n\t\t\t_isEatable:" + isEatable();
	}
}
