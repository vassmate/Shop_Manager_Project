package shop.logger;

public interface IShopLogger {

	public static final int REPLENISH = 1;
	public static final int REMOVE = 2;
	public static final int BUY = 3;
	public static final int PRODUCTLIST_REQUEST = 4;

	public void addReplenishLog(String logInfo);

	public void addRemoveLog(String logInfo);

	public void addBuyLog(String logInfo);

	public void addProductListRequestLog(String logInfo);

	public void closeLogging();
}
