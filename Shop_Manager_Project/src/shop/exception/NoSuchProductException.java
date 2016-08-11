package shop.exception;

import shop.Product;
import shop.ShopException;

public class NoSuchProductException extends ShopException {
	private static final long serialVersionUID = 4188335021957233522L;

	public NoSuchProductException() {
		super("\nProduct is not on the list!");
	}

	public NoSuchProductException(Product product) {
		super("\nYou can not replenish: " + product + "\nProduct is not on the list!");
	}
}
