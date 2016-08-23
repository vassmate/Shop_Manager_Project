package shop.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;

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

	public static void clearLog(File logfile) {
		if (logfile.exists()) {
			try {
				OutputStream newLogfileStream = new FileOutputStream(logfile);
				newLogfileStream.close();
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
	}

	public static void writeLog(BufferedWriter bWriter, String logString) {
		try {
			bWriter.write(logString);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public static File makeNewLogfile(File logfolderPath, File logfileName) {
		try {
			if (!logfolderPath.exists()) {
				logfolderPath.mkdir();
			}
			File newLogFile = new File(logfolderPath.getAbsolutePath() + "/" + logfileName.getName());
			if (!newLogFile.exists()) {
				OutputStream newLogfileStream = new FileOutputStream(newLogFile);
				newLogfileStream.close();
				return newLogFile;
			}
			return newLogFile;
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}

	public static BufferedWriter makeNewLogfileWriter(File logfile) {
		try {
			FileWriter fw = new FileWriter(logfile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			return bw;
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}
}
