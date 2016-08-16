package shop.logger;

import java.time.LocalDateTime;

public interface IShopLogRegistration {

	public LocalDateTime getDate();

	public String getLogInfo();

	public boolean isReplenish();

	public boolean isRemove();

	public boolean isBuy();

	public boolean isProductListRequest();

}
