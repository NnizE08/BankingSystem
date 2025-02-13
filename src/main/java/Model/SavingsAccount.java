package Model;

public class SavingsAccount implements Account {
	private double balance;

	public SavingsAccount(double initialBalance) {
		this.balance = initialBalance;
	}

	@Override
	public void deposit(double amount) {
		if (amount > 0) {
			balance += = amount;
			System.out.printf("Deposit: ");
		} else {
			System.out.println("Invalid Amount");
		}
	}

	@Override
	public void withdraw(double amount) {
		if (amount > 0 && amount <= balance) {
			balance -= amount;
			System.out.println("Withrawal " + amount);
		} else {
			System.out.println("Not Enough balance / Invalid");
		}
	}

	@Override
	public double getBalance() {
		return balance;
	}
}
