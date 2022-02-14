import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankController bank = new BankController(scanner);

        bank.open();
    }
}
