import Model.*;
import Service.AccountService;

public class Main {
	public static void main(String[] args) {
		AccountService accountService = new AccountService();

		// Create Accounts
		Account savings = accountService.createAccount(AccountType.SAVINGS, 200, 0); // Savings Account
		Account checking = accountService.createAccount(AccountType.CHECKING, 500, 100); // Checking Account

		System.out.println("\n--- Initial Balances ---");
		System.out.println("Savings Balance: " + savings.getBalance());
		System.out.println("Checking Balance: " + checking.getBalance());

		// Test Deposits
		System.out.println("\n--- Depositing Money ---");
		accountService.deposit(savings, 150);
		accountService.deposit(checking, 200);

		// Test Withdrawals
		System.out.println("\n--- Withdrawing Money ---");
		accountService.withdraw(savings, 50);
		accountService.withdraw(checking, 700); // Should fail due to overdraft limit?
		accountService.withdraw(checking, 50);

		// Test Transfers
		System.out.println("\n--- Transferring Money ---");
		accountService.transfer(checking, savings, 100);
		accountService.transfer(savings, checking, 500); // Should fail due to insufficient funds!

		// Final Balances
		System.out.println("\n--- Final Balances ---");
		System.out.println("Savings Balance: " + savings.getBalance());
		System.out.println("Checking Balance: " + checking.getBalance());
	}
}
