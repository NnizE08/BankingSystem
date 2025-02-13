import Model.*;
import Service.AccountService;

public class Main {
	public static void main(String[] args) {
		AccountService accountService = new AccountService();

		// Create accounts
		Account savings = accountService.createAccount(AccountType.SAVINGS, 500, 0);
		Account checking = accountService.createAccount(AccountType.CHECKING, 300, 200);

		// Display account info
		System.out.println("Created Savings Account: " + savings.getAccountNumber() + " | Balance: $" + savings.getBalance());
		System.out.println("Created Checking Account: " + checking.getAccountNumber() + " | Balance: $" + checking.getBalance());

		// Deposit funds
		accountService.deposit(savings, 200);
		accountService.deposit(checking, 100);

		// Withdraw funds
		accountService.withdraw(savings, 150);
		accountService.withdraw(checking, 350); // Should test overdraft

		// Transfer funds
		accountService.transfer(savings, checking, 100);

		// Display final balances
		System.out.println("Final Savings Balance: $" + savings.getBalance());
		System.out.println("Final Checking Balance: $" + checking.getBalance());
	}
}