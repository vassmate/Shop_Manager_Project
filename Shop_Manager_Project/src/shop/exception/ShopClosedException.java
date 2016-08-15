package shop.exception;

import shop.ShopException;

public class ShopClosedException extends ShopException {
	private static final long serialVersionUID = 248883564621017788L;
	
	public ShopClosedException() {
		super("Action forbidden! Shop is still closed!");
	}

}
