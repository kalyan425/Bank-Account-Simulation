package bank_account_simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Account{
	protected String accountHolder;
	protected double balance;
	protected List<String> transactionHistory=new ArrayList<>();
	public Account(String accountHolder,double initialBalance) {
		this.accountHolder=accountHolder;
		this.balance=initialBalance;
		transactionHistory.add("Account created with balance:"+initialBalance);
	}
	public void deposit(double amount) {
		balance+=amount;
		transactionHistory.add("Deposited:"+amount+" New Balance:"+balance);
		System.out.println("Deposited"+amount+" Current Balance "+balance);
	}
	public void withdraw(double amount) {
		if(amount<=balance) {
			balance-=amount;
			transactionHistory.add("Withdraw:"+amount+"New Balance "+balance);
			System.out.println("Withdraw"+amount+" Current Balance: "+balance);
		}else {
			System.out.println("Insufficient Balance!");
		}
	}
	public void showBalance() {
		System.out.println("Current Balance"+balance);
	}
	public void showTransactionHistory() {
		System.out.println("\nTransaction History for"+accountHolder+":");
		for(String record : transactionHistory) {
			System.out.println(record);
		}
	}
}
class SavingsAccount extends Account{
	private double interestRate;
	public SavingsAccount(String accountHolder,double initialBalance,double interestRate) {
		super(accountHolder,initialBalance);
		this.interestRate=interestRate;
	}
	@Override
	public void withdraw(double amount) {
		if(balance-amount<500) {
			System.out.println("Cannot withdraw minimum balance is 500 required..");
		}else {
			super.withdraw(amount);
		}
	}
	public void addInterest() {
		double interest=balance*interestRate/100;
		balance+=interest;
		transactionHistory.add("Interest added:"+interest+" New Balance:"+balance);
		System.out.println("Interest added: "+interest+" .Current Balance "+balance);
	}
}

public class BankAccountSimulation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc =new Scanner(System.in);
		System.out.print("Enter account holder name:");
		String name=sc.nextLine();
		SavingsAccount acc=new SavingsAccount(name,1000,5.0);
		int choice;
		do {
			System.out.println("\n--Bank Menu--");
			System.out.println("1.Deposit");
			System.out.println("2.Withdraw");
			System.out.println("3.Show Balance");
			System.out.println("4.Add Interest");
			System.out.println("5.Transaction History");
			System.out.println("6.Exit");
			System.out.println("Choosen an option: ");
			choice=sc.nextInt();
			switch(choice) {
			case 1:
				System.out.println("Enter amount to deposit: ");
				acc.deposit(sc.nextDouble());
				break;
				
			case 2:
				System.out.println("Enter amount to withdraw: ");
				acc.withdraw(sc.nextDouble());
			case 3:
				acc.showBalance();
				break;
			case 4:
				acc.addInterest();
				break;
			case 5:
				acc.showTransactionHistory();
				break;
			case 6:
				System.out.println("Thank you for banking with us!");
			default:
				System.out.println("Invalid option try again....");
			}
		}while(choice!=6);
		sc.close();

	}

}
