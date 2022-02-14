import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        //challenge one
//        System.out.println(challengeOne(10, 3));

        //challenge five
//        System.out.println(challengeFive("Able was I,,,,,klj ere I saw Elba"));
//        System.out.println(challengeFive("A man, a plan, a canal, Panama"));
//        System.out.println(challengeFive("Desserts,  stressed"));
//        System.out.println(challengeFive("Kayak"));

        //challenge nine
//        System.out.println(ackermann(0, 0));
//        System.out.println(ackermann(0, 1));
//        System.out.println(ackermann(1, 1));
//        System.out.println(ackermann(1, 2));
//        System.out.println(ackermann(1, 3));
//        System.out.println(ackermann(2, 2));
//        System.out.println(ackermann(3, 2));
    }

    private static int challengeOne(int x, int y) {
        if (x == 1) {
            return y;
        }
        return y + challengeOne(--x, y);
    }

    private static boolean challengeFive(String str) {
        str = str.toLowerCase();
        int start = 0;
        int end = str.length() - 1;

        if (str.trim().length() < 2 || start == end) return true;

        //going from left to right, find first index of a letter -> %%$car
        if (!Character.isLetterOrDigit(str.charAt(start))) {
            start++;
            while (!Character.isLetterOrDigit(str.charAt(start))) {
                start++;
            }
        }

        //going from right to left, find first index of a letter
        if (!Character.isLetterOrDigit(str.charAt(end))) {
            end--;
            while (!Character.isLetterOrDigit(str.charAt(end))) {
                end--;
            }
        }

        //if start and end letter are same, keep going
        if (str.charAt(start) == str.charAt(end)) {
            return challengeFive(str.substring(start + 1, end));
        }

        //letters were different, return false
        return false;
    }


    private static int ackermann(int m, int n) {
        if (m == 0) {
            return n + 1;
        } else if (n == 0) {
            return ackermann(m - 1, 1);
        } else {
            return ackermann(m - 1, ackermann(m, n  - 1));
        }

    }

}
