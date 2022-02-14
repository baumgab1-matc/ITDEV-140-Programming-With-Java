
import java.util.Random;

//helper class to make mock data
public class DataLoader {

    private final CardGenerator generator = new CardGenerator();
    private final Random random = new Random();

    public void writeMockDataToDB() {
        int i = 0;

        while (i++ < 50) {
            String number = generator.generate();
            String pin = "1111";

            //get random amount for balance
            int lower = 20;
            int upper = 10_000;
            int balance = random.nextInt(upper - lower + 1) + lower;

            Card card = new Card(number, BankController.hashPin(pin), balance, 0, false);
            Database.getInstance().createCard(card);
        }

    }

}
