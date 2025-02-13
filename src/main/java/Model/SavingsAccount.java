package Model;

import Util.AccountUtils;



public class SavingsAccount implements Account {
	private String accountNumber;
	private double balance;
	private final double interestRate = 0.025; //2.5% to if converted
	private final double minimumBalance = 100.0; // min balance naten
	private final AccountType accountType = AccountType.SAVINGS;


	public SavingsAccount(double initialBalance) {
		this.accountNumber = AccountUtils.generateAccountNumber(); // Unique ac#
		this.balance = initialBalance;

	}

	@Override
	public void deposit(double amount) {
		if (amount > 0) {
			balance += amount;
			System.out.println("Deposit: ");
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
		if ((balance - amount) < minimumBalance) {
			System.out.println("Withdrawal denied! Minimum balance of " + minimumBalance + " must be maintained"); //below 100 deny agad
			return;
		}
		balance -= amount; // if d na meet conditions, pasok agad sa balance minus withrawal amount
		System.out.println("Withdrawal: " + amount);
	}

	public String getAccountNumber(){
		return accountNumber;
	}
	@Override
	public double getBalance() {
		return balance;
	}

	public void addInterest() {
		double interest = (balance * interestRate) / 12; //12 months pr yr
		balance += interest;
		System.out.println("Interest of " + interest);
	}

	public AccountType getAccountType() {
		return accountType;
	}
}
