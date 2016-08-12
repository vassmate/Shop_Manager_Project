package shop.behavior;

import java.util.Iterator;

public interface IShopBehavior {

	public Iterator<Long> getProductsIterator();

	public String getName();

	public String getAddress();

	public String getOwner();

}
