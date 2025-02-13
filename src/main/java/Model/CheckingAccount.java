package Model;

import Util.AccountUtils;

public class CheckingAccount implements Account {
	private String accountNumber;
	private double balance;
	private final double overdraftLimit;
	private static final double MONTHLY_FEE = 12.0; // 12 charge per month overdraft
	private final AccountType accountType = AccountType.CHECKING;

	public CheckingAccount(double initialBalance, double overdraftLimit) {
		this.accountNumber = AccountUtils.generateAccountNumber(); // Unique ac#
		this.balance = initialBalance;
		this.overdraftLimit = overdraftLimit;
	}

	@Override
	public void deposit(double amount) {
		if (amount > 0) {
			balance += amount;
			System.out.println("Deposit: " + amount);
		} else {
			System.out.println("Invalid Amount");
		}
	}

	@Override
	public void withdraw(double amount) {
		if (amount <= 0) {
			System.out.println("Invalid withdrawal amount!");
			return;
		}
		if (amount > balance + overdraftLimit) {
			System.out.println("Insufficient funds! Overdraft limit exceeded");
			return;
		}
		balance -= amount;
		System.out.println("Withdrawal: " + amount);
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	@Override
	public double getBalance() {
		return balance;
	}

	public void addMonthlyFee() {
		if (balance >= 12) {
			balance -= 12;
			System.out.println("Monthly Fee of 12 applied");
		} else {
			System.out.println("Insufficient Funds for Monthly Fee");
		}
	}

	public void deductMonthlyFee() {
		if (balance - MONTHLY_FEE < -overdraftLimit) {
			System.out.println("Insufficient funds for monthly fee deduction!");
			return;
		}
		balance -= MONTHLY_FEE;
		System.out.println("Monthly fee deducted: $" + MONTHLY_FEE);
	}

	public double getOverdraftLimit() {
		return overdraftLimit;
	}

	public AccountType getAccountType() {
		return accountType;
	}
}

