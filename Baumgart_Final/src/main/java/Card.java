public class Card {

    private final String number;
    private final String pinHash;
    private double balance = 0;
    private int invalidLoginAttempts = 0;
    private boolean isLocked = false;

    public Card(String number, String pinHash) {
        this.number = number;
        this.pinHash = pinHash;
    }

    public Card(String number, String pinHash, double balance, int invalidLoginAttempts, boolean isLocked) {
        this.number = number;
        this.pinHash = pinHash;
        this.balance = balance;
        this.invalidLoginAttempts = invalidLoginAttempts;
        this.isLocked = isLocked;
    }

    public String getNumber() {
        return number;
    }

    public String getPinHash() {
        return pinHash;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getInvalidLoginAttempts() {
        return invalidLoginAttempts;
    }

    public void invalidLogin() {
        invalidLoginAttempts++;

        if (invalidLoginAttempts >= 3) {
            isLocked = true;
        }

    }

    public boolean isLocked() {
        return isLocked;
    }

    public void resetLoginAttempts() {
        isLocked = false;
        invalidLoginAttempts = 0;
    }

}
