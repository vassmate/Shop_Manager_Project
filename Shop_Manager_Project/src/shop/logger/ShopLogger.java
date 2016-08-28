package shop.logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

public class ShopLogger implements IShopLogger {

	private File logFolder;
	private File logfile;
	private BufferedWriter bufferedLogfileWriter;
	private final String windowsDefaultFolder = "C:/Users/Public/Documents";
	private final String linuxDefaultFolder = "/home";
	private final String defaultFilename = "logfile.txt";
	private final String defaultFolderName = "smp_log";

	public ShopLogger() {
		this.logFolder = new File(windowsDefaultFolder);
		if (!getLogFolder().exists()) {
			this.logFolder = new File(linuxDefaultFolder + "/" + defaultFolderName);
		} else {
			this.logFolder = new File(windowsDefaultFolder + "/" + defaultFolderName);
		}
		this.logfile = new File(getLogFolder().getAbsolutePath() + "/" + defaultFilename);
	}

	public ShopLogger(String filePath, String fileName) {
		this.logFolder = new File(filePath);
		this.logfile = new File(getLogFolder().getAbsolutePath() + "/" + fileName);
	}

	public File getLogfile() {
		return logfile;
	}

	public File getLogFolder() {
		return logFolder;
	}

	private BufferedWriter getBufferedLogfileWriter() {
		return bufferedLogfileWriter;
	}

	private void setBufferedLogfileWriter(BufferedWriter bufferedLogfileWriter) {
		this.bufferedLogfileWriter = bufferedLogfileWriter;
	}

	public boolean isLogFileExists() {
		return getLogfile().exists();
	}

	public boolean isWriterExists() {
		if (getBufferedLogfileWriter() == null) {
			return false;
		}
		return true;
	}

	@Override
	public void addReplenishLog(String logInfo) {
		ShopLogRegistration shopLogReg = new ShopLogRegistration(IShopLogger.REPLENISH, LocalDateTime.now(), logInfo);
		checkLogAndWrite(shopLogReg);

	}

	@Override
	public void addRemoveLog(String logInfo) {
		ShopLogRegistration shopLogReg = new ShopLogRegistration(IShopLogger.REMOVE, LocalDateTime.now(), logInfo);
		checkLogAndWrite(shopLogReg);
	}

	@Override
	public void addBuyLog(String logInfo) {
		ShopLogRegistration shopLogReg = new ShopLogRegistration(IShopLogger.BUY, LocalDateTime.now(), logInfo);
		checkLogAndWrite(shopLogReg);
	}

	@Override
	public void addProductListRequestLog(String logInfo) {
		ShopLogRegistration shopLogReg = new ShopLogRegistration(IShopLogger.PRODUCTLIST_REQUEST, LocalDateTime.now(),
				logInfo);
		checkLogAndWrite(shopLogReg);
	}

	@Override
	public void closeLogging() {
		try {
			if (isWriterExists()) {
				getBufferedLogfileWriter().flush();
				getBufferedLogfileWriter().close();
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public Iterator<String> getShopLogs() {
		ArrayList<String> shopLogs = new ArrayList<>();
		try {
			if (isLogFileExists()) {
				BufferedReader bReader = new BufferedReader(new FileReader(getLogfile()));
				String line = bReader.readLine();
				while (line != null) {
					shopLogs.add(line);
					line = bReader.readLine();
				}
				bReader.close();
				return shopLogs.iterator();
			}
			return shopLogs.iterator();
		} catch (Exception ex) {
			System.out.println(ex);
			return shopLogs.iterator();
		}
	}

	public void clearLog() {
		if (isLogFileExists()) {
			IShopLogger.clearLog(getLogfile());
			setBufferedLogfileWriter(IShopLogger.makeNewLogfileWriter(getLogfile()));
			IShopLogger.writeLog(getBufferedLogfileWriter(), "_LogfileWasClearedAt:" + LocalDateTime.now());
			closeLogging();
		}
	}

	private void checkLogAndWrite(ShopLogRegistration shopLogReg) {
		if (isLogFileExists()) {
			if (isWriterExists()) {
				IShopLogger.writeLog(getBufferedLogfileWriter(), shopLogReg.toString());
			} else {
				setBufferedLogfileWriter(IShopLogger.makeNewLogfileWriter(getLogfile()));
				IShopLogger.writeLog(getBufferedLogfileWriter(), shopLogReg.toString());
			}
		} else {
			IShopLogger.makeNewLogfile(getLogFolder(), getLogfile());
			setBufferedLogfileWriter(IShopLogger.makeNewLogfileWriter(getLogfile()));
			IShopLogger.writeLog(getBufferedLogfileWriter(), shopLogReg.toString());
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
				return "\n\n_Code" + getCode() + ":Replenish" + "_Date:"
						+ getDate().format(DateTimeFormatter.ISO_LOCAL_DATE) + "_Time:"
						+ getDate().format(DateTimeFormatter.ISO_LOCAL_TIME) + "_LogInfo:" + getLogInfo();
			}
			if (isRemove()) {
				return "\n\n_Code" + getCode() + ":Remove" + "_Date:"
						+ getDate().format(DateTimeFormatter.ISO_LOCAL_DATE) + "_Time:"
						+ getDate().format(DateTimeFormatter.ISO_LOCAL_TIME) + "_LogInfo:" + getLogInfo();
			}
			if (isBuy()) {
				return "\n\n_Code" + getCode() + ":Buy" + "_Date:" + getDate().format(DateTimeFormatter.ISO_LOCAL_DATE)
						+ "_Time:" + getDate().format(DateTimeFormatter.ISO_LOCAL_TIME) + "_LogInfo:" + getLogInfo();
			}
			if (isProductListRequest()) {
				return "\n\n_Code" + getCode() + ":ProductListRequest" + "_Date:"
						+ getDate().format(DateTimeFormatter.ISO_LOCAL_DATE) + "_Time:"
						+ getDate().format(DateTimeFormatter.ISO_LOCAL_TIME) + "_LogInfo:" + getLogInfo();
			}
			return "\n\n_Code" + getCode() + ":UnknownAction" + "_Date:"
					+ getDate().format(DateTimeFormatter.ISO_LOCAL_DATE) + "_Time:"
					+ getDate().format(DateTimeFormatter.ISO_LOCAL_TIME) + "_LogInfo:" + getLogInfo();
		}
	}
}
