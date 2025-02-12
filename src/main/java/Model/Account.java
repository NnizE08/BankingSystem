package Model;

public interface Account {
	void deposit(double amount);
	boolean withdraw(double amount);
	void displayAccountInfo();
	String getAccountNumber();
	double getBalance();
}
