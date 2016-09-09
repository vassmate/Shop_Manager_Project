package plaza;

import java.util.Iterator;
import java.util.Vector;

import shop.Shop;

public class PlazaImpl implements Plaza {

	private Vector<Shop> shops;
	private boolean isOpen;

	public PlazaImpl(Vector<Shop> shops) {
		this.shops = shops;
		open();
	}

	@Override
	public void addShop(Shop o) {
		if (this.shops != null && this.shops.size() > 0) {
			if (!this.shops.contains(o)) {
				if (this.shops.capacity() - this.shops.size() > 0) {
					this.shops.add(o);
				} else {
					this.shops.addElement(o);
				}
			}
		} else {
			setShops(new Vector<Shop>());
			this.shops.add(o);
		}
	}

	@Override
	public void deleteShop(Shop o) {
		if (this.shops != null && this.shops.size() > 0) {
			if (this.shops.contains(o)) {
				this.shops.remove(o);
			}
		}
	}

	@Override
	public void open() {
		setOpen(true);
	}

	@Override
	public void close() {
		setOpen(false);
	}

	@Override
	public boolean isOpen() {
		return this.isOpen;
	}

	@Override
	public Iterator<Shop> getAllShops() {
		Vector<Shop> newShopVector = new Vector<>();
		for (Shop shop : this.shops) {
			newShopVector.add(shop);
		}
		Iterator<Shop> shopIter = newShopVector.iterator();
		return shopIter;
	}

	private void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	private void setShops(Vector<Shop> shops) {
		this.shops = shops;
	}
}
