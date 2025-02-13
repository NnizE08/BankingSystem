package Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionLogger {
	private static final String LOG_FILE = "transactions.txt";

	public static void logTransaction(String accountNumber, String type, double amount, double balance) {
		try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
			String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			String logEntry = String.format("[%s] Account: %s | Type: %s | Amount: $%.2f | Balance: $%.2f",
					timestamp, accountNumber, type, amount, balance);

			writer.write(logEntry + System.lineSeparator()); // Cross-platform newline
			writer.flush(); // Ensure data is written immediately
		} catch (IOException e) {
			System.err.println("Error writing to log file: " + e.getMessage());
			e.printStackTrace(); // Helps in debugging
		}
	}

	public static void logCustom(String message) {
		try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
			writer.write(message + "\n");
		} catch (IOException e) {
			System.out.println("Error writing to log file: " + e.getMessage());
		}
	}
}