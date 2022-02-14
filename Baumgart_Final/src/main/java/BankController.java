import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.Scanner;

public class BankController {

    private final Scanner scanner;
    private final CardGenerator cardGenerator;
    private boolean isOpen = true;
    private final Database database = Database.getInstance();
    private final Logger logger;

    public BankController(Scanner scanner) {
        this.scanner = scanner;
        this.cardGenerator = new CardGenerator();
        this.logger = new Logger("logs/" + LocalDate.now() + ".txt");
    }

    public void open() {

        while (isOpen) {
            String menu = "\n1. Create an account\n2. Log into account\n3. Admin\n> ";
            logger.logAndPrint(menu);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createAccount();
                    break;

                case "2":
                    loginToAccount();
                    break;

                case "3":
                    adminLogin();
                    break;

                default:
                    logger.logAndPrintln("\nUnknown Bank Operation. Try again!");
            }
        }
    }

    private void createAccount() {
        logger.logAndPrintln("\nWelcome to account setup! Type 'x' at any time to exit.\n");
        String cardNumber = cardGenerator.generate();
        logger.logAndPrintln("A card number has been created for you.");
        logger.logAndPrintln("Your card number is: " + cardNumber);

        logger.logAndPrintln("\nA pin needs to be created. DO NOT FORGET YOUR PIN.");
        String pinGiven;

        while (true) {
            logger.logAndPrint("Give a four digit pin: ");
            pinGiven = scanner.nextLine().trim();

            //check if user wants to stop
            if (pinGiven.equalsIgnoreCase("x")) {
                logger.logAndPrintln("\nCancelled creating account!");
                return;
            }

            //validate pin, make sure length is four and all numbers
            if (pinGiven.length() != 4 || !pinGiven.matches("[0-9]{4}")) {
                logger.logAndPrintln("Invalid PIN given!");
            } else {
                break;
            }
        }

        //hash pin
        String hashPin = hashPin(pinGiven);

        Card card = new Card(cardNumber, hashPin);

        if (database.createCard(card)) {
            logger.logAndPrintln("\nAccount Created!");
        } else {
            logger.logAndPrintln("Error creating account");
        }
    }

    private void loginToAccount() {

        //if login is successful returns card, else returns empty optional
        Optional<Card> loginCard = login();

        //if empty, user did not enter valid login credentials
        if (loginCard.isEmpty()) return;

        //hashed pin is in database, can now show login menu
        logger.logAndPrintln("\nLogin successful!");
        Card card = loginCard.get();

        String userMenu = "\n1. Balance\n" +
                "2. Deposit\n" +
                "3. Withdraw\n" +
                "4. Transfer\n" +
                "5. Close account\n" +
                "6. Log out\n> ";

        boolean isLoggedIn = true;

        while (isLoggedIn) {
            logger.logAndPrint(userMenu);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    displayBalance(card);
                    break;

                case "2":
                    deposit(card);
                    break;

                case "3":
                    withdraw(card);
                    break;

                case "4":
                    transferFrom(card);
                    break;

                case "5":
                    if (closeAccount(card)) {
                        isLoggedIn = false;
                    }
                    break;

                case "6":
                    logger.logAndPrintln("\nYou have successfully logged out!");
                    isLoggedIn = false;
                    break;

                default:
                    logger.logAndPrintln("\nUnknown Operation. Try again!");
            }
        }
    }

    private Optional<Card> login() {
        logger.logAndPrint("Enter card number: ");
        String number = scanner.nextLine().trim();

        //check if card number is in db
        Optional<Card> foundCard = database.fetchCardByNumber(number);

        //check for not card found or locked card
        if (foundCard.isEmpty()) {
            logger.logAndPrintln("No account found with given account number");
            return Optional.empty();
        } else if (foundCard.get().isLocked()) {
            logger.logAndPrintln("Your card has been locked. Contact admin.");
            return Optional.empty();
        }

        boolean isEnteringPin = true;
        Optional<Card> cardWithPin;

        while (isEnteringPin) {
            logger.logAndPrint("Enter PIN (type 'x' to stop): ");
            String pin = scanner.nextLine().trim();

            if (pin.equalsIgnoreCase("x")) {
                return Optional.empty();
            }

            //hash pin
            String hashPin = hashPin(pin);

            //card was in database, now compare it with given pin
            cardWithPin = database.fetchCardByNumberAndPin(number, hashPin);

            //if empty, user gave wrong password
            if (cardWithPin.isEmpty()) {
                //increment invalid logins by 1
                foundCard.get().invalidLogin();

                if (foundCard.get().getInvalidLoginAttempts() == 3) {
                    logger.logAndPrintln("No more attempts. Card has been locked, contact admin.");
                    //update database
                    database.updateCard(foundCard.get());
                    return Optional.empty();
                } else {
                    logger.logAndPrintln("Invalid pin given! Remaining attempts " + (3 - foundCard.get().getInvalidLoginAttempts()));
                }

            } else {
                //pin was correct, reset login attempts
                foundCard.get().resetLoginAttempts();

                //break loop
                isEnteringPin = false;
            }

            //update database
            database.updateCard(foundCard.get());
        }

        return foundCard;
    }


    private void displayBalance(Card card) {
        logger.logAndPrintln(String.format("\nAccount balance: $%.2f", card.getBalance()));
    }

    private void deposit(Card card) {
        logger.logAndPrint("\nAmount to deposit: ");

        try {
            double amount = Double.parseDouble(scanner.nextLine());

            if (amount <= 0) {
                logger.logAndPrintln("Invalid deposit. Must be amount greater than zero.");
                return;
            }

            double oldBalance = card.getBalance();
            double newBalance = oldBalance + amount;
            card.setBalance(newBalance);

            //update card in database
            if (database.updateCard(card)) {
                logger.logAndPrintln("Success!");
                logger.logAndPrintln(String.format("Previous balance: $%.2f", oldBalance));
                logger.logAndPrintln(String.format("New balance: $%.2f", newBalance));
            } else {
                logger.logAndPrintln("Error depositing money");
            }

        } catch (NumberFormatException e) {
            logger.logAndPrintln("Invalid deposit. Must be amount greater than zero.");
        }
    }

    private void withdraw(Card card) {
        logger.logAndPrint("\nAmount to withdraw: ");

        try {
            double toWithdraw = Double.parseDouble(scanner.nextLine());

            if (toWithdraw <= 0) {
                logger.logAndPrintln("Invalid withdraw. Amount must greater than zero.");
                return;
            }

            //make sure user has enough money
            if (card.getBalance() - toWithdraw < 0) {
                logger.logAndPrintln("Do not have enough founds to withdraw.");
                return;
            }

            double oldBalance = card.getBalance();
            double newBalance = oldBalance - toWithdraw;
            card.setBalance(newBalance);

            if (database.updateCard(card)) {
                logger.logAndPrintln("Success!");
                logger.logAndPrintln(String.format("Previous balance: $%.2f", oldBalance));
                logger.logAndPrintln(String.format("New balance: $%.2f", newBalance));
            } else {
                logger.logAndPrintln("Error withdrawing money");
            }

        } catch (NumberFormatException e) {
            logger.logAndPrintln("Invalid withdraw. Must be amount greater than zero.");
        }
    }

    private void transferFrom(Card fromCard) {
        if (fromCard.getBalance() <= 0) {
            logger.logAndPrintln("No funds available to transfer.");
            return;
        }

        logger.logAndPrintln("Starting money transfers. Type 'x' at any time to exit.\n");
        logger.logAndPrint("Account number to transfer funds to: ");
        String to = scanner.nextLine().trim();

        if (to.equalsIgnoreCase("x")) {
            return;
        }

        //don't allow accounts to transfer to themselves
        if (fromCard.getNumber().equals(to)) {
            logger.logAndPrintln("Cannot transfer funds to yourself.");
            return;
        }

        //search for to transfer card in db
        Optional<Card> toFind = database.fetchCardByNumber(to);

        if (toFind.isEmpty()) {
            logger.logAndPrintln("No card found with number " + to);
            return;
        }

        Card toCard = toFind.get();
        boolean isTransferring = true;

        while (isTransferring) {
            logger.logAndPrint("How much would you like to transfer: ");
            String input = scanner.nextLine().toLowerCase().trim();

            if (input.equals("x")) {
                return;
            }

            try {
                double amount = Double.parseDouble(input);

                //make sure amount is not negative
                if (amount < 0) {
                    logger.logAndPrintln("Transfer amount must be positive.");
                    continue;
                }

                //check if account has enough money for transfer
                if (fromCard.getBalance() - amount < 0) {
                    logger.logAndPrintln("Not enough funds to transfer.");
                    continue;
                }

                logger.logAndPrint(String.format("$%.2f will be transferred from your account to account %s, are you sure?(y/n) ", amount, toCard.getNumber()));
                String choice = scanner.nextLine().toLowerCase().trim();

                if (!choice.equals("n") && !choice.equals("no")) {
                    fromCard.setBalance(fromCard.getBalance() - amount);
                    toCard.setBalance(toCard.getBalance() + amount);

                    database.updateCard(toCard);
                    database.updateCard(fromCard);

                    logger.logAndPrintln("Transfer completed.");
                    isTransferring = false;
                }


            } catch (NumberFormatException e) {
                logger.logAndPrintln("Invalid amount given. Try again");
            }

        }
    }

    private boolean closeAccount(Card toClose) {
        if (toClose.getBalance() > 0) {
            logger.logAndPrintln("Cannot close account with money in it. Please withdraw first.");
            return false;
        }

        logger.logAndPrint("Closing accounts cannot be undo, are you sure?(y/n): ");
        String response = scanner.nextLine().toLowerCase().trim();

        if (response.equals("no") || response.equals("n")) {
            logger.logAndPrintln("Account not closed");
            return false;
        }

        if (database.deleteCard(toClose)) {
            logger.logAndPrintln("Card removed");
            logger.logAndPrintln("Account no longer active, logging off.");
            return true;
        }

        return false;
    }

    private void adminLogin() {
        logger.logAndPrint("Enter user: ");
        String user = scanner.nextLine().trim();

        logger.logAndPrint("Enter password: ");
        String password = scanner.nextLine().trim();

        if (!user.equals("admin") && !password.equals("password")) return;

        logger.logAndPrintln("\nLogin successful!");
        String adminMenu = "\n1. Unlock Account\n" +
                            "2. Read Logs\n" +
                            "3. Close Application\n" +
                            "4. Log out\n> ";

        boolean isLoggedIn = true;

        while (isLoggedIn) {
            logger.logAndPrint(adminMenu);
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    unlockAccount();
                    break;

                case "2":
                    readLogs();
                    break;

                case "3":
                    logger.logAndPrint("Application closed at: " + LocalTime.now());
                    isOpen = false;
                    isLoggedIn = false;
                    break;

                case "4":
                    isLoggedIn = false;
                    break;

                default:
                    logger.logAndPrintln("\nUnknown selection");
            }

        }
    }

    private void unlockAccount() {
        logger.logAndPrint("Enter account number: ");
        String number = scanner.nextLine().trim();

        //find card in db
        Optional<Card> toFind = database.fetchCardByNumber(number);

        if (toFind.isEmpty()) {
            logger.logAndPrintln("No card found with number " + number);
            return;
        }

        Card card = toFind.get();

        //check if card is even locked
        if (!card.isLocked()) {
            logger.logAndPrintln("Card is not locked.");
            return;
        }

        //card was locked, unlock it
        card.resetLoginAttempts();
        database.updateCard(card);
        logger.logAndPrintln("Account has been unlocked.");
    }

    private void readLogs() {
        logger.logAndPrint("Date of log (yyyy-MM-dd): ");
        String date = scanner.nextLine().trim();

        //check if file even exists
        if (!Files.exists(Path.of("logs/" + date + ".txt"))) {
            System.out.println("No log found.");
        } else {
            try {
                File myObj = new File("logs/" + date + ".txt");
                Scanner myReader = new Scanner(myObj);
                System.out.println("\n***** START OF LOG *****");
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    System.out.println(data);
                }
                System.out.println("\n***** END OF LOG *****");
                myReader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    // applies Sha256 to pin and returns its hash.
    public static String hashPin(String pin) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(pin.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte elem : hash) {
                String hex = Integer.toHexString(0xff & elem);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
