package Util;

import Model.Account;
import Exceptions.InvalidAmountException;
import Exceptions.InsufficientFundsException;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

public class AccountUtils {

	private static final NumberFormat DECIMAL_FORMAT = new DecimalFormat("#0.##");

	public static String generateAccountNumber() {
		Random random = new Random();
		int accountNumber = 100000 + random.nextInt(900000);
		return "Account# " + accountNumber;
	}

	public static String formatBalance(double balance) {
		return DECIMAL_FORMAT.format(balance);
	}
	public static void validateDeposit(double amount) throws InvalidAmountException {
		if (amount <= 0) {
			throw new InvalidAmountException("Deposit amount must be greater than zero.");
		}
	}

	public static void validateWithdrawal(Account account, double amount) throws InvalidAmountException, InsufficientFundsException {
		if (amount <= 0) {
			throw new InvalidAmountException("Withdrawal amount must be greater than zero.");
		}
		if (account.getBalance() < amount) {
			throw new InsufficientFundsException("Insufficient funds for withdrawal.");
		}
	}

	public static void validateTransfer(Account from, Account to, double amount) throws InvalidAmountException, InsufficientFundsException {
		if (amount <= 0) {
			throw new InvalidAmountException("Transfer amount must be greater than zero.");
		}
		if (from.getBalance() < amount) {
			throw new InsufficientFundsException("Transfer failed: Insufficient funds.");
		}
	}
}
