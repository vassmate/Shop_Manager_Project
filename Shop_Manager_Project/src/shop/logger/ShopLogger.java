package shop.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ShopLogger implements IShopLogger {

	private File logfile;
	private FileWriter logfileWriter;
	private BufferedWriter bufferedLogfileWriter;

	public ShopLogger() {
		this.logfile = new File("logfile.txt");
	}

	public ShopLogger(File file) {
		this.logfile = file;
	}

	public File getLogfile() {
		return logfile;
	}

	public boolean isLogFileExists() {
		return getLogfile().exists();
	}

	private FileWriter getLogfileWriter() {
		return logfileWriter;
	}

	private BufferedWriter getBufferedLogfileWriter() {
		return bufferedLogfileWriter;
	}

	private void setLogfileWriter(FileWriter logfileWriter) {
		this.logfileWriter = logfileWriter;
	}

	private void setBufferedLogfileWriter(BufferedWriter bufferedLogfileWriter) {
		this.bufferedLogfileWriter = bufferedLogfileWriter;
	}

	@Override
	public void addReplenishLog(String logInfo) {
		ShopLogRegistration shopLogReg = new ShopLogRegistration(IShopLogger.REPLENISH, LocalDateTime.now(), logInfo);
		writeLog(shopLogReg.toString());
	}

	@Override
	public void addRemoveLog(String logInfo) {
		ShopLogRegistration shopLogReg = new ShopLogRegistration(IShopLogger.REMOVE, LocalDateTime.now(), logInfo);
		writeLog(shopLogReg.toString());
	}

	@Override
	public void addBuyLog(String logInfo) {
		ShopLogRegistration shopLogReg = new ShopLogRegistration(IShopLogger.BUY, LocalDateTime.now(), logInfo);
		writeLog(shopLogReg.toString());
	}

	@Override
	public void addProductListRequestLog(String logInfo) {
		ShopLogRegistration shopLogReg = new ShopLogRegistration(IShopLogger.PRODUCTLIST_REQUEST, LocalDateTime.now(),
				logInfo);
		writeLog(shopLogReg.toString());
	}

	@Override
	public void closeLogging() {
		try {
			if (getLogfileWriter() != null && getBufferedLogfileWriter() != null) {
				getBufferedLogfileWriter().flush();
				getBufferedLogfileWriter().close();
				getLogfileWriter().flush();
				getLogfileWriter().close();
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	private void writeLog(String shopLogReqString) {
		try {
			if (getLogfileWriter() == null && getBufferedLogfileWriter() == null) {
				makeNewLogfileWriter();
			}
			if (isLogFileExists()) {
				getBufferedLogfileWriter().write(shopLogReqString);
			} else {
				makeNewLogfile();
				getBufferedLogfileWriter().write(shopLogReqString);
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	private void makeNewLogfile() {
		try {
			if (!isLogFileExists()) {
				OutputStream newLogfileStream = new FileOutputStream(getLogfile());
				newLogfileStream.close();
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	private void makeNewLogfileWriter() {
		try {
			setLogfileWriter(new FileWriter(getLogfile(), true));
			setBufferedLogfileWriter(new BufferedWriter(getLogfileWriter()));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public class ShopLogRegistration implements IShopLogRegistration {
		private int code;
		private LocalDateTime date;
		private String logInfo;

		public ShopLogRegistration(int code, LocalDateTime date, String logInfo) {
			this.code = code;
			this.date = date;
			this.logInfo = logInfo;
		}

		public LocalDateTime getDate() {
			return date;
		}

		public String getLogInfo() {
			return logInfo;
		}

		private int getCode() {
			return code;
		}

		public boolean isReplenish() {
			if (getCode() == 1) {
				return true;
			}
			return false;
		}

		public boolean isRemove() {
			if (getCode() == 2) {
				return true;
			}
			return false;
		}

		public boolean isBuy() {
			if (getCode() == 3) {
				return true;
			}
			return false;
		}

		public boolean isProductListRequest() {
			if (getCode() == 4) {
				return true;
			}
			return false;
		}

		@Override
		public String toString() {
			if (isReplenish()) {
				return "\n_Code" + getCode() + ":Replenish" + "_Date:"
						+ getDate().format(DateTimeFormatter.ISO_LOCAL_DATE) + "_Time:"
						+ getDate().format(DateTimeFormatter.ISO_LOCAL_TIME) + "_LogInfo:" + getLogInfo();
			}
			if (isRemove()) {
				return "\n_Code" + getCode() + ":Remove" + "_Date:" + getDate().format(DateTimeFormatter.ISO_LOCAL_DATE)
						+ "_Time:" + getDate().format(DateTimeFormatter.ISO_LOCAL_TIME) + "_LogInfo:" + getLogInfo();
			}
			if (isBuy()) {
				return "\n_Code" + getCode() + ":Buy" + "_Date:" + getDate().format(DateTimeFormatter.ISO_LOCAL_DATE)
						+ "_Time:" + getDate().format(DateTimeFormatter.ISO_LOCAL_TIME) + "_LogInfo:" + getLogInfo();
			}
			if (isProductListRequest()) {
				return "\n_Code" + getCode() + ":ProductListRequest" + "_Date:"
						+ getDate().format(DateTimeFormatter.ISO_LOCAL_DATE) + "_Time:"
						+ getDate().format(DateTimeFormatter.ISO_LOCAL_TIME) + "_LogInfo:" + getLogInfo();
			}
			return "\n_Code" + getCode() + ":UnknownAction" + "_Date:"
					+ getDate().format(DateTimeFormatter.ISO_LOCAL_DATE) + "_Time:"
					+ getDate().format(DateTimeFormatter.ISO_LOCAL_TIME) + "_LogInfo:" + getLogInfo();
		}
	}
}
