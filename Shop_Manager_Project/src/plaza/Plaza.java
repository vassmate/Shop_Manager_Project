package plaza;

import java.util.Iterator;

import shop.Shop;

public interface Plaza {

	public void addShop(Shop o);

	public void deleteShop(Shop o);

	public void open();

	public void close();

	public boolean isOpen();

	public Iterator<Shop> getAllShops();
}
