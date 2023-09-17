import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bank {
    private List<BankAccount> accounts;
    private Scanner scanner;

    public Bank() {
        this.accounts = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void createAccount(String accountNumber, String accountHolder) {
        BankAccount account = new BankAccount(accountNumber, accountHolder);
        accounts.add(account);
        System.out.println("Account created for " + accountHolder + " with account number " + accountNumber);
    }

    public BankAccount findAccount(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public void deposit(BankAccount account, double amount) {
        account.deposit(amount);
        System.out.println("Deposited $" + amount + " into " + account.getAccountHolder() + "'s account.");
        System.out.println("New balance: $" + account.getBalance());
    }

    public void withdraw(BankAccount account, double amount) {
        boolean success = account.withdraw(amount);
        if (success) {
            System.out.println("Withdrawal of $" + amount + " from " + account.getAccountHolder() + "'s account successful.");
        } else {
            System.out.println("Withdrawal of $" + amount + " from " + account.getAccountHolder() + "'s account failed (insufficient funds).");
        }
        System.out.println("New balance: $" + account.getBalance());
    }

    public void run() {
        while (true) {
            System.out.println("\nBank Menu:");
            System.out.println("1. Create an Account");
            System.out.println("2. Find an Account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter account number: ");
                    String accountNumber = scanner.nextLine();
                    System.out.print("Enter account holder's name: ");
                    String accountHolder = scanner.nextLine();
                    createAccount(accountNumber, accountHolder);
                    break;

                case 2:
                    System.out.print("Enter account number to find: ");
                    String findAccountNumber = scanner.nextLine();
                    BankAccount foundAccount = findAccount(findAccountNumber);
                    if (foundAccount != null) {
                        System.out.println("Account holder: " + foundAccount.getAccountHolder());
                        System.out.println("Balance: $" + foundAccount.getBalance());
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter account number to deposit into: ");
                    String depositAccountNumber = scanner.nextLine();
                    BankAccount depositAccount = findAccount(depositAccountNumber);
                    if (depositAccount != null) {
                        System.out.print("Enter the amount to deposit: $");
                        double depositAmount = scanner.nextDouble();
                        scanner.nextLine(); // Consume the newline character
                        deposit(depositAccount, depositAmount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter account number to withdraw from: ");
                    String withdrawAccountNumber = scanner.nextLine();
                    BankAccount withdrawAccount = findAccount(withdrawAccountNumber);
                    if (withdrawAccount != null) {
                        System.out.print("Enter the amount to withdraw: $");
                        double withdrawAmount = scanner.nextDouble();
                        scanner.nextLine(); // Consume the newline character
                        withdraw(withdrawAccount, withdrawAmount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting the Bank Application.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.run();
    }
}
