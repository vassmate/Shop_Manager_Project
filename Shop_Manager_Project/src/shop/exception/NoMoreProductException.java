package shop.exception;

import shop.Product;
import shop.ShopException;

public class NoMoreProductException extends ShopException {
	private static final long serialVersionUID = -7133581864368860925L;

	public NoMoreProductException() {

	}

	public NoMoreProductException(Product product) {
		super("\nNo such product on stock:" + product);
	}
}
