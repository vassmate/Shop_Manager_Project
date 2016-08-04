package products;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Milk {

	private String company;
	private int price;
	private LocalDate warrant;
	private double capacity;
	private double dripping;

	public Milk(String company, int price, String warrant, double capacity, double dripping) {
		this.company = company;
		this.price = price;

		LocalDate warr = LocalDate.parse(warrant, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		this.warrant = warr;

		this.capacity = capacity;
		this.dripping = dripping;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public LocalDate getWarrant() {
		return warrant;
	}

	public void setWarrant(String date) {
		LocalDate warr = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		this.warrant = warr;
	}

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(float capacity) {
		this.capacity = capacity;
	}

	public double getDripping() {
		return dripping;
	}

	public void setDripping(float dripping) {
		this.dripping = dripping;
	}

	public boolean isDrinkable() {
		LocalDate currentDate = LocalDate.now();
		return this.warrant.compareTo(currentDate) > 0;
	}

	@Override
	public String toString() {
		return "Company: " + getCompany() + ", Price: " + getPrice() + ", Warrant: " + getWarrant() + ", Capacity: "
				+ getCapacity() + ", Dripping: " + getDripping() + ", Drinkable: " + isDrinkable();
	}
}
