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
	private Iterator<String> shopLogs;
	private boolean isWriterClosed = true;
	private final String windowsDefaultFolder = "C:/Users/Public/Documents";
	private final String linuxDefaultFolder = "/home";
	private final String defaultFilename = "logfile.txt";
	private final String defaultFolderName = "smp_log";

	public ShopLogger() {
		this.logFolder = new File(windowsDefaultFolder);
		checkAndSetDefaultFolder();
		this.logfile = new File(getLogFolder().getAbsolutePath() + "/" + defaultFilename);
	}

	public ShopLogger(String filePath, String fileName) {
		this.logFolder = new File(filePath);
		checkAndSetCustomFolder();
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
		this.isWriterClosed = false;
		this.bufferedLogfileWriter = bufferedLogfileWriter;
	}

	public boolean isLogFileExists() {
		return getLogfile().exists();
	}

	public boolean isWriterExists() {
		if (this.isWriterClosed) {
			return false;
		}
		return true;
	}

	/*
	 * The Override annotation is not necessary but it makes clear which methods
	 * are overridden from the IShopLogger interface
	 */

	// File writing functions
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

	// File reading functions
	@Override
	public Iterator<String> getShopLogs() {
		addProductListRequestLog("\n\t_All logs were requested.");
		closeLogging();
		refreshAllShopLogs();
		return this.shopLogs;
	}

	@Override
	public Iterator<String> getReplenishLogs() {
		return getLogIterator("_Code1", "\n\t_Replenish logs were requested.");
	}

	@Override
	public Iterator<String> getRemoveLogs() {
		return getLogIterator("_Code2", "\n\t_Removed product logs were requested.");
	}

	@Override
	public Iterator<String> getBuyLogs() {
		return getLogIterator("_Code3", "\n\t_Bought product logs were requested.");
	}

	@Override
	public Iterator<String> getProductListRequestLogs() {
		return getSpecifiedLogIterator("_Code4");
	}

	@Override
	public void closeLogging() {
		try {
			if (isWriterExists()) {
				getBufferedLogfileWriter().flush();
				getBufferedLogfileWriter().close();
				this.isWriterClosed = true;
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	// File clearing function
	public void clearLog() {
		if (isLogFileExists()) {
			IShopLogger.clearLog(getLogfile());
			setBufferedLogfileWriter(IShopLogger.makeNewLogfileWriter(getLogfile()));
			IShopLogger.writeLog(getBufferedLogfileWriter(), "_LogfileWasClearedAt:" + LocalDateTime.now());
			closeLogging();
		}
	}

	// Private functions for handling logging specific actions

	private void checkAndSetDefaultFolder() {
		if (!getLogFolder().exists()) {
			this.logFolder = new File(linuxDefaultFolder + "/" + defaultFolderName);
		} else {
			this.logFolder = new File(windowsDefaultFolder + "/" + defaultFolderName);
		}
	}

	private void checkAndSetCustomFolder() {
		if (!getLogFolder().getAbsolutePath().startsWith("C:/")
				&& !getLogFolder().getAbsolutePath().startsWith("/home")) {
			checkAndSetDefaultFolder();
		}
	}

	private Iterator<String> getAllShopLogs() {
		refreshAllShopLogs();
		return this.shopLogs;
	}

	private void refreshAllShopLogs() {
		this.shopLogs = getShopLogsFromFile();
	}

	private Iterator<String> getLogIterator(String logCode, String logMessage) {
		Iterator<String> specificIterator;
		addProductListRequestLog(logMessage);
		closeLogging();
		specificIterator = getSpecifiedLogIterator(logCode);
		return specificIterator;
	}

	private Iterator<String> getSpecifiedLogIterator(String logCode) {
		String currentLog;
		ArrayList<String> specifiedLogList = new ArrayList<>();
		Iterator<String> allShopLogs = getAllShopLogs();

		while (allShopLogs.hasNext()) {
			currentLog = allShopLogs.next();
			if (currentLog.startsWith(logCode) && (logCode.equals("_Code4") || logCode.equals("_Code2"))) {
				specifiedLogList.add(currentLog);
				specifiedLogList.add(allShopLogs.next());
			} else if (currentLog.startsWith(logCode)) {
				specifiedLogList.add(currentLog);
				specifiedLogList.add(allShopLogs.next());
				specifiedLogList.add(allShopLogs.next());
				specifiedLogList.add(allShopLogs.next());
				specifiedLogList.add(allShopLogs.next());
			}
		}
		return specifiedLogList.iterator();
	}

	private Iterator<String> getShopLogsFromFile() {
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

	/*
	 * The ShopLogRegistration class describes a log which will be written in
	 * the log file. The log structure will look like: -header: includes the
	 * registration code and registration date and time -body: includes
	 * information about the registration itself.
	 */
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
				return getReplenishLogString();
			}
			if (isRemove()) {
				return getRemoveLogString();
			}
			if (isBuy()) {
				return getBuyLogString();
			}
			if (isProductListRequest()) {
				return getProductListRequestString();
			}
			return getUnknownActionString();
		}

		private String getReplenishLogString() {
			return "\n\n_Code" + getCode() + ":Replenish" + "_Date:"
					+ getDate().format(DateTimeFormatter.ISO_LOCAL_DATE) + "_Time:"
					+ getDate().format(DateTimeFormatter.ISO_LOCAL_TIME) + "_LogInfo:" + getLogInfo();
		}

		private String getRemoveLogString() {
			return "\n\n_Code" + getCode() + ":Remove" + "_Date:" + getDate().format(DateTimeFormatter.ISO_LOCAL_DATE)
					+ "_Time:" + getDate().format(DateTimeFormatter.ISO_LOCAL_TIME) + "_LogInfo:" + getLogInfo();
		}

		private String getBuyLogString() {
			return "\n\n_Code" + getCode() + ":Buy" + "_Date:" + getDate().format(DateTimeFormatter.ISO_LOCAL_DATE)
					+ "_Time:" + getDate().format(DateTimeFormatter.ISO_LOCAL_TIME) + "_LogInfo:" + getLogInfo();
		}

		private String getProductListRequestString() {
			return "\n\n_Code" + getCode() + ":ProductListRequest" + "_Date:"
					+ getDate().format(DateTimeFormatter.ISO_LOCAL_DATE) + "_Time:"
					+ getDate().format(DateTimeFormatter.ISO_LOCAL_TIME) + "_LogInfo:" + getLogInfo();
		}

		private String getUnknownActionString() {
			return "\n\n_Code" + getCode() + ":UnknownAction" + "_Date:"
					+ getDate().format(DateTimeFormatter.ISO_LOCAL_DATE) + "_Time:"
					+ getDate().format(DateTimeFormatter.ISO_LOCAL_TIME) + "_LogInfo:" + getLogInfo();
		}
	}
}
