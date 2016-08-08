package products;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Milk {
	private final int LITER = 1000;
	private final int HALF_LITER = 500;
	private final int CUP = 250;
	private final double WHOLE = 3.8;
	private final double SEMI_SKIMMED = 2.8;

	private long barcode;
	private String company;
	private LocalDate warrant;
	private double capacity;
	private double dripping;

	public Milk(long barcode, String company, String warrant, double capacity, double dripping) {
		this.barcode = barcode;
		this.company = company;

		LocalDate warr = LocalDate.parse(warrant, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		this.warrant = warr;

		if (capacity > 700) {
			this.capacity = LITER;
		} else if (capacity < 700 && capacity > 400) {
			this.capacity = HALF_LITER;
		} else {
			this.capacity = CUP;
		}

		if (dripping > 2.8) {
			this.dripping = WHOLE;
		} else {
			this.dripping = SEMI_SKIMMED;
		}
	}

	public long getBarcode() {
		return barcode;
	}

	public void setBarcode(long barcode) {
		this.barcode = barcode;
	}

	public String getCompany() {
		return company;
	}

	public LocalDate getWarrant() {
		return warrant;
	}

	public double getCapacity() {
		return capacity;
	}

	public double getDripping() {
		return dripping;
	}

	public boolean isDrinkable() {
		LocalDate currentDate = LocalDate.now();
		return this.warrant.compareTo(currentDate) > 0;
	}

	@Override
	public String toString() {
		return "\n\t\t\t_Barcode:" + getBarcode() + "\n\t\t\t_Company:" + getCompany() + "\n\t\t\t_Warrant:"
				+ getWarrant() + "\n\t\t\t_Capacity:" + getCapacity() + "\n\t\t\t_Dripping:" + getDripping()
				+ "\n\t\t\t_Drinkable:" + isDrinkable();
	}
}
