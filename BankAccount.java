import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;
    private List<Transaction> transactions;

    public BankAccount(String accountNumber, String accountHolder) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add(new Transaction(TransactionType.DEPOSIT, amount));
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            transactions.add(new Transaction(TransactionType.WITHDRAWAL, amount));
            return true;
        }
        return false;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public List<Transaction> getTransactionHistory() {
        return transactions;
    }

    public void printTransactionHistory() {
        System.out.println("Transaction History for Account: " + accountNumber);
        for (Transaction transaction : transactions) {
            System.out.println(transaction.toString());
        }
    }

    private enum TransactionType {
        DEPOSIT,
        WITHDRAWAL
    }

    private static class Transaction {
        private TransactionType type;
        private double amount;

        public Transaction(TransactionType type, double amount) {
            this.type = type;
            this.amount = amount;
        }

        @Override
        public String toString() {
            return type.toString() + ": $" + amount;
        }
    }
}

public class BankMain {
    public static void main(String[] args) {
        Bank bank = new Bank();

        // Create random accounts
        for (int i = 0; i < 5; i++) {
            BankAccount randomAccount = RandomAccountGenerator.generateRandomAccount();
            bank.createAccount(randomAccount.getAccountNumber(), randomAccount.getAccountHolder());
        }

        bank.run();
    }
}
