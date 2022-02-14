import java.util.Random;
import java.util.Scanner;

public class Main {

    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //chapter three
//        challengeFour();
//        challengeSeventeen();
//        challengeEighteen();

        //chapter four
//        challengeEight();
//        challengeSixteen();
        challengeTwentyTwo();
    }

    private static void challengeFour() {
        System.out.println("*** Chapter 3 Challenge 4: Test Scores and Grades ***");

        System.out.print("Enter test score 1: ");
        double score1 = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter test score 2: ");
        double score2 = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter test score 3: ");
        double score3 = Double.parseDouble(scanner.nextLine());

        double average = (score1 + score2 + score3) / 3;
        String letterGrade;

        if (average < 60) {
            letterGrade = "F";
        } else if (average < 70) {
            letterGrade = "D";
        } else if (average < 80) {
            letterGrade = "C";
        } else if (average < 90) {
            letterGrade = "B";
        } else {
            letterGrade = "A";
        }

        System.out.printf("The average of scores %.2f, %.2f and %.2f is %.2f which gives a letter grade of %s", score1, score2, score3, average, letterGrade);
    }

    private static void challengeSeventeen() {
        System.out.println("*** Chapter 3 Challenge 17: Wi-Fi Diagnostics Tree ***");

        //try rebooting the computer
        System.out.println("Reboot the computer and try to connect.");
        if (isProblemFixed()) return;

        //try rebooting the router and try to connect
        System.out.println("Reboot the router and try to connect.");
        if (isProblemFixed()) return;

        //check the cables plugged in
        System.out.println("Make sure the cables between the router & modem are plugged in firmly.");
        if (isProblemFixed()) return;

        //try moving router
        System.out.println("Move the router to a new location and try to connect.");
        if (isProblemFixed()) return;

        //no solutions worked, get new router
        System.out.println("Get a new router.");
    }

    //helper method to ask if computer problem is fixed
    private static boolean isProblemFixed() {
        System.out.print("Did that fix the problem? ");
        String response = scanner.nextLine().toLowerCase().trim();

        return response.equals("yes");
    }

    private static void challengeEighteen() {
        System.out.println("*** Chapter 3 Challenge 18: Restaurant Selector ***");

        System.out.print("Is anyone in your party a vegetarian? ");
        boolean isVegetarian = scanner.nextLine().equals("yes");

        System.out.print("Is anyone in your party a vegan? ");
        boolean isVegan = scanner.nextLine().equals("yes");

        System.out.print("Is anyone in your party gluten-free? ");
        boolean isGlutenFree = scanner.nextLine().equals("yes");

        String joeBurgers = "\tJoe's Gourmet Burgers";
        String mainStreetPizza = "\tMain Street Pizza Company";
        String cornerCafe = "\tCorner Cafe";
        String mamasItalian = "\tMama's Fine Italian";
        String chefsKitchen = "\tThe Chef's Kitchen";

        //we have three people who can be true or false so we have a total of 2^3 options to chose from
        // F F F, F F T, F T T....

        System.out.println("Here are your restaurant choices:");

        if (!isVegetarian && !isVegan && !isGlutenFree) {
            System.out.println(joeBurgers);
            System.out.println(mainStreetPizza);
            System.out.println(cornerCafe);
            System.out.println(mamasItalian);
            System.out.println(chefsKitchen);
        } else if (!isVegetarian && !isVegan && isGlutenFree) {
            System.out.println(mainStreetPizza);
            System.out.println(cornerCafe);
            System.out.println(chefsKitchen);
        } else if (!isVegetarian && isVegan && isGlutenFree) {
            System.out.println(cornerCafe);
            System.out.println(chefsKitchen);
        } else if (!isVegetarian && isVegan && !isGlutenFree) {
            System.out.println(cornerCafe);
            System.out.println(chefsKitchen);
        } else if (isVegetarian && isVegan && isGlutenFree) {
            System.out.println(cornerCafe);
            System.out.println(chefsKitchen);
        } else if (isVegetarian && isVegan && !isGlutenFree) {
            System.out.println(cornerCafe);
            System.out.println(chefsKitchen);
        } else if (isVegetarian && !isVegan && isGlutenFree) {
            System.out.println(mainStreetPizza);
            System.out.println(cornerCafe);
            System.out.println(chefsKitchen);
        } else if (isVegetarian && !isVegan && !isGlutenFree) {
            System.out.println(mainStreetPizza);
            System.out.println(cornerCafe);
            System.out.println(mainStreetPizza);
            System.out.println(chefsKitchen);
        }
    }

    private static void challengeEight() {
        System.out.println("*** Chapter 4 Challenge 8: Average Rainfall ***");

        System.out.print("Number of years: ");
        int years = getYears();
        int months = years * 12;
        double totalRainfall = 0;

        for (int i = 0; i < years; i++) {
            for (int j = 0; j < 12; j++) {
                System.out.print("Enter inches of rainfall for month " + (j + 1) + ": ");
                totalRainfall += getInchesOfRainfall();
            }
        }

        System.out.println("\nNumber of months: " + months);
        System.out.println("Total inches of rainfall: " + totalRainfall);
        System.out.printf("Average rainfall per month: %.2f", totalRainfall / months);
    }

    //helper method for getting valid years
    private static int getYears() {
        while (true) {
            int years = Integer.parseInt(scanner.nextLine());

            if (years < 1) {
                System.out.println("ERROR! Years cannot be less than 1. Try again!");
            } else {
                return years;
            }

        }
    }

    //helper method for getting valid inches of rainfall
    private static double getInchesOfRainfall() {
        while (true) {
            double inches = Double.parseDouble(scanner.nextLine());

            if (inches < 0) {
                System.out.println("ERROR! Cannot have negative rainfall. Try again!");
            } else {
                return inches;
            }

        }
    }

    private static void challengeSixteen() {
        System.out.println("*** Chapter 4 Challenge 16: Budget Analysis ***");

        System.out.print("Enter amount you have budgeted for this month: ");
        double budgetAmount = Double.parseDouble(scanner.nextLine());

        double monthsTotal = 0;
        System.out.println("Enter this months expenses. Enter -1 to stop loop");
        while (true) {
            System.out.print("Enter expense amount: ");
            double amount = Double.parseDouble(scanner.nextLine());

            if (amount == -1) break;

            monthsTotal += amount;
        }

        System.out.println("\nYour total budget amount was: " + budgetAmount);
        System.out.println("Your monthly expenses were: " + monthsTotal);

        //check if over under budget
        if (monthsTotal == budgetAmount) {
            System.out.println("You were exactly on budget this month!");
        } else if (monthsTotal < budgetAmount) {
            System.out.printf("You stayed under budget this month by: %.2f", (budgetAmount - monthsTotal));
        } else {
            System.out.printf("You went over budget this month by %.2f", (monthsTotal - budgetAmount));
        }
    }

    private static void challengeTwentyTwo() {
        System.out.println("*** Chapter 4 Challenge 22: Slot Machine Simulation ***");

        Random random = new Random();
        boolean isGambling = true;
        String image1;
        String image2;
        String image3;

        while (isGambling) {
            System.out.print("Enter amount of money to insert into slot machine: ");
            double amount = Double.parseDouble(scanner.nextLine());

            System.out.println("Pulling lever...");

            //get three random numbers
            image1 = getImage(random);
            image2 = getImage(random);
            image3 = getImage(random);

            System.out.println("You pulled a " + image1 + ", " + image2 + " and " + image3);
            int matches = getMatchCount(image1, image2, image3);

            if (matches == 0) {
                System.out.println("No images match! You won no money!");
            } else if (matches == 1) {
                System.out.println("You pulled a match! You doubled your money to " + 2*amount);
            } else {
                System.out.println("All three match!!! You tripled your money to " + 3*amount);
            }

            System.out.print("\nWould you like to play again? (yes/no) ");
            String choice = scanner.nextLine();

            if (choice.equals("no")) {
                isGambling = false;
            }
        }
    }

    //helper method to get a random image
    private static String getImage(Random random) {
        int idx = random.nextInt(6);

        if (idx == 0) {
            return "Cherries";
        } else if (idx == 1) {
            return "Oranges";
        } else if (idx == 2) {
            return "Plums";
        } else if (idx == 3) {
            return "Bells";
        } else if (idx == 4) {
            return "Melons";
        } else {
            return "Bars";
        }
    }

    //helper method to see how many images match
    private static int getMatchCount(String str1, String str2, String str3) {
        //no matches
        if (!str1.equals(str2) && !str1.equals(str3) && !str2.equals(str3)) {
            return 0;
        }

        //all matches
        if (str1.equals(str2) && str1.equals(str3)) {
            return 2;
        }

        //two strings match
        return 1;
    }

}
