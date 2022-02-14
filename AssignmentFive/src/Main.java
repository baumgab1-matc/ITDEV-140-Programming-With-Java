import chapterSevenChallengeEight.GradeBook;
import chapterSevenChallengeFifteen.PopulationData;
import chapterSevenChallengeNineteen.Trivia;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //chapter 7
        //challenge 8
//        GradeBook gradeBook = new GradeBook(scanner);
//        gradeBook.fill();

        //challenge 15
//        PopulationData populationData = new PopulationData(args[0]);
//        populationData.displayResults();

        //challenge 19
//        Trivia trivia = new Trivia(scanner);
//        trivia.play();

        //chapter 9
        //challenge 1
//        String gravity = challengeOne("gravity");
//        System.out.println(gravity);

        //challenge 9
//        challengeNine();

        challengeSixteen();
    }


    private static String challengeOne(String str) {
        System.out.println("*** Chapter 9 Challenge 1 - Backward String ***");

        int i = 0;
        int j = str.length() - 1;
        char[] holder = str.toCharArray();

        while (i < j) {
            char temp = holder[i];
            holder[i] = holder[j];
            holder[j] = temp;
            i++;
            j--;
        }

        return new String(holder);

//        return new StringBuilder(str).reverse().toString();
    }

    private static void challengeNine() {
        System.out.println("*** Chapter 9 Challenge 9 - Sum of Digits in a String ***");
        System.out.print("Enter a series of digits: ");
        String input = scanner.nextLine();
        int sum = 0;
        int highest = Integer.MIN_VALUE;
        int lowest = Integer.MAX_VALUE;

        for (int c : input.toCharArray()) {

            if (!Character.isDigit(c)) continue;

            int num = Character.getNumericValue(c);

            if (num > highest) {
                highest = num;
            }

            if (num < lowest) {
                lowest = num;
            }

            sum += num;
        }

        System.out.println("Sum is: " + sum);
        System.out.println("Highest digit is: " + highest);
        System.out.println("Lowest digit is: " + lowest);
    }

    private static void challengeSixteen() {
        System.out.println("*** Chapter 9 Challenge 16 - Morse Code Converter ***");
        //get morse code translations
        Map<Character, String> morseCodeMap = buildMap();

        System.out.print("Enter test to translate to morse code: ");
        String input = scanner.nextLine().toUpperCase().trim();

        StringBuilder morseCode = new StringBuilder();

        for (char c : input.toCharArray()) {
            morseCode.append(morseCodeMap.get(c)).append(" ");
        }

        System.out.println(morseCode);
    }

    private static Map<Character, String> buildMap() {
        Map<Character, String> map = new HashMap<>();
        map.put(' ', " ");
        map.put(',', "--.--");
        map.put('.', ".-.-.-");
        map.put('?', "..--..");
        map.put('0', "----");
        map.put('1', ".----");
        map.put('2', "..---");
        map.put('3', "...--");
        map.put('4', "....-");
        map.put('5', ".....");
        map.put('6', "-....");
        map.put('7', "--...");
        map.put('8', "---..");
        map.put('9', "----.");
        map.put('A', ".-");
        map.put('B', "-...");
        map.put('C', "-.-.");
        map.put('D', "-..");
        map.put('E', ".");
        map.put('F', "..-.");
        map.put('G', "--.");
        map.put('H', "....");
        map.put('I', "..");
        map.put('J', ".---");
        map.put('K', "-.-");
        map.put('L', ".-..");
        map.put('M', "--");
        map.put('N', "-.");
        map.put('O', "---");
        map.put('P', ".--.");
        map.put('Q', "--.-");
        map.put('R', ".-.");
        map.put('S', "...");
        map.put('T', "-");
        map.put('U', "..-");
        map.put('V', "...-");
        map.put('W', ".--");
        map.put('X', "-..-");
        map.put('Y', "-.--");
        map.put('Z', "--..");

        return map;
    }

}
