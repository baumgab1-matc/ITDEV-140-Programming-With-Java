import java.util.Random;

public class CardGenerator {

    public String generate() {
        //visa is made of as follows
        //issuer number    issuer id   user id       check number
        //4                48_531      515_882_284   9

        int randomNumber;
        String issuerNumber = "4";

        //issuer ID ranges from 10_000 - 99_999
        randomNumber = getRandomNumber(10_000, 99_999);
        String issuerID = String.valueOf(randomNumber);

        //user id ranges from 100_000_000 - 999_999_999
        randomNumber = getRandomNumber(100_000_000, 999_999_999);
        String userID = String.valueOf(randomNumber);

        String checkSum = String.valueOf(getCheckSum(issuerNumber + issuerID + userID));

        return issuerNumber + issuerID + userID + checkSum;
    }

    private int getCheckSum(String number) {
        String byTwo = multiplyOddIndexesByTwo(number, 0);
        int sum = sumDigits(byTwo);

        //now answer the question, sum + x = multiple of 10
        if (sum % 10 == 0) {
            return 0;
        } else {
            int rounded = (10 - sum % 10) + sum; //rounds up nearest 10, 62 -> 70
            return rounded - sum;
        }
    }

    //Multiplies odd index digits by two and subtracts 9 from products over 9
    private String multiplyOddIndexesByTwo(String number, int idx) {
        if (number.length() == 0) {
            return number;
        }

        int digit = Character.getNumericValue(number.charAt(0));
        int toReturn = idx % 2 == 0 ? digit * 2 : digit;

        //if toReturn is over 9 need to subtract 9
        if (toReturn > 9) {
            toReturn -= 9;
        }

        return toReturn + multiplyOddIndexesByTwo(number.substring(1), ++idx);
    }

    private int sumDigits(String number) {
        if (number.length() == 0){
            return 0;
        }

        int digit = Character.getNumericValue(number.charAt(0));

        return digit + sumDigits(number.substring(1));
    }

    private int getRandomNumber(int lower, int upper) {
        Random random = new Random();
        return random.nextInt(upper - lower + 1) + lower;
    }

}
