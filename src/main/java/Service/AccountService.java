package Service;

import Model.Account;
import Model.AccountType;
import Model.CheckingAccount;
import Model.SavingsAccount;
import Util.TransactionLogger;
import Exceptions.InsufficientFundsException;
import Exceptions.InvalidAmountException;
import Util.AccountUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AccountService {

	public Account createAccount(AccountType type, double initialBalance, double overdraftLimit) {
		if (initialBalance < 0) {
			throw new InvalidAmountException("Initial Balance cannot be Negative");
		}

		Account account;

		switch (type) {
			case SAVINGS:
				if (initialBalance < 100) {
					throw new InvalidAmountException("Savings account 100 minimum required");
				}
				account = new SavingsAccount(initialBalance);
				break;
			case CHECKING:
				if (overdraftLimit < 0) {
					throw new InvalidAmountException("Overdraft Cannot be Negative");
				}
				account = new CheckingAccount(initialBalance, overdraftLimit);
				break;
			default:
				throw new IllegalArgumentException("Invalid Account Type");
		}

		logAccountCreation(account, type);
		return account;
	}

	public void deposit(Account account, double amount) {
		try {
			AccountUtils.validateDeposit(amount);
			account.deposit(amount);
			System.out.println("Deposited " + amount);
			TransactionLogger.logTransaction(account.getAccountNumber(), "Deposit", amount, account.getBalance());
		} catch (InvalidAmountException e) {
			System.out.println(e.getMessage());
		}
	}


	public void withdraw(Account account, double amount) {
		try {
			AccountUtils.validateWithdrawal(account, amount);
			account.withdraw(amount);
			System.out.println("Withdrawn: " + amount);
			TransactionLogger.logTransaction(account.getAccountNumber(), "Withdrawal", amount, account.getBalance());
		} catch (InvalidAmountException | InsufficientFundsException e) {
			System.out.println(e.getMessage());
		}
	}

	public void transfer(Account from, Account to, double amount) {
		try {
			AccountUtils.validateTransfer(from, to, amount);
			from.withdraw(amount);
			to.deposit(amount);
			System.out.println("Transferred " + amount + " successfully");
			TransactionLogger.logTransaction(from.getAccountNumber(), "Transfer Out", amount, from.getBalance());
			TransactionLogger.logTransaction(to.getAccountNumber(), "Transfer In", amount, to.getBalance());
		} catch (InvalidAmountException | InsufficientFundsException e) {
			System.out.println(e.getMessage());
		}
	}

	private void logAccountCreation(Account account, AccountType type) { //logs here
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String formattedBalance = AccountUtils.formatBalance(account.getBalance());
		String logEntry = "[" + timestamp + "] Account Created: " + account.getAccountNumber() + " | Type: " + type + " | Balance: " + account.getBalance();
		TransactionLogger.logCustom(logEntry);
	}
}
