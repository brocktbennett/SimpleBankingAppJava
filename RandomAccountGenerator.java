import java.util.Random;

public class RandomAccountGenerator {
    private static final String[] ACCOUNT_HOLDERS = {
        "John Doe", "Alice Smith", "Bob Johnson", "Emily Brown", "Michael Wilson"
    };

    private static final double MIN_INITIAL_BALANCE = 100.0;
    private static final double MAX_INITIAL_BALANCE = 10000.0;

    private static final Random random = new Random();

    public static BankAccount generateRandomAccount() {
        String accountNumber = generateRandomAccountNumber();
        String accountHolder = generateRandomAccountHolder();
        double initialBalance = generateRandomInitialBalance();

        return new BankAccount(accountNumber, accountHolder, initialBalance);
    }

    private static String generateRandomAccountNumber() {
        int randomNumber = random.nextInt(10000) + 1000; // Generate a random 4-digit number
        return "ACCT" + randomNumber;
    }

    private static String generateRandomAccountHolder() {
        int randomIndex = random.nextInt(ACCOUNT_HOLDERS.length);
        return ACCOUNT_HOLDERS[randomIndex];
    }

    private static double generateRandomInitialBalance() {
        return MIN_INITIAL_BALANCE + (MAX_INITIAL_BALANCE - MIN_INITIAL_BALANCE) * random.nextDouble();
    }
}
