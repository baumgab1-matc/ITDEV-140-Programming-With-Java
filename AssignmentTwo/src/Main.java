import java.util.Scanner;

//Brent Baumgart
//ITDEV-140
//Assignment Two

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        challenge5();
        challenge7();
        challenge9();
        challenge12();
        challenge20();
        challenge21();
    }

    private static void challenge5() {
        System.out.println("******** Challenge 5 - Sales Prediction ********");

        double percent = .62;
        int sales = 4_600_000;

        double totalGeneration = percent * sales;
        System.out.println(totalGeneration);
    }

    private static void challenge7() {
        System.out.println("\n\n\n******** Challenge 7 - Sales Tax ********");

        double salesTax = .04;
        double countyTax = .02;

        System.out.print("Amount of purchase: ");
        double amount = Double.parseDouble(scanner.nextLine());

        double salesTaxAmount = amount * salesTax;
        double countyTaxAmount = amount * countyTax;
        double totalTax = salesTaxAmount + countyTaxAmount;
        double amountWithTax = amount + totalTax;

        System.out.println("Purchase without tax: " + amount);
        System.out.println("Sales tax (4%): " + salesTaxAmount);
        System.out.println("County tax (2%): " + countyTaxAmount);
        System.out.println("Total sales tax: " + totalTax);
        System.out.println("Total price: " + amountWithTax);
    }

    private static void challenge9() {
        System.out.println("\n\n\n******** Challenge 9 - MPG ********");

        System.out.print("Number of miles driven: ");
        double milesDriven = Double.parseDouble(scanner.nextLine());

        System.out.print("Gallons of gas used: ");
        double gallonsOfGas = Double.parseDouble(scanner.nextLine());

        double mpg = milesDriven / gallonsOfGas;

        System.out.println("Miles-per-gallon: " + mpg);
    }

    private static void challenge12() {
        System.out.println("\n\n\n******** Challenge 12 - String Manipulator ********");

        System.out.print("Enter your favorite city: ");
        String favoriteCity = scanner.nextLine();


        System.out.println("The number of characters in " + favoriteCity + " is " + favoriteCity.length());
        System.out.println("To uppercase " + favoriteCity.toUpperCase());
        System.out.println("To lowercase " + favoriteCity.toLowerCase());
        System.out.println("The first character in " + favoriteCity + " is " + favoriteCity.charAt(0));
    }

    private static void challenge20() {
        System.out.println("\n\n\n******** Challenge 20 - Planting Grapevines ********");

        System.out.print("Enter the length of the row in feet: ");
        double rowLength = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter the amount of space used by and end-post assembly in feet: ");
        double endPostSpace = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter the amount of space between the vines in feet: ");
        double spaceBetweenVines = Double.parseDouble(scanner.nextLine());

        double numberOfGrapeVines = (rowLength - 2 * endPostSpace) / spaceBetweenVines;

        System.out.println("The number of grapevines that will fit in this row is: " + numberOfGrapeVines);
    }

    private static void challenge21() {
        System.out.println("\n\n\n******** Challenge 21 - Compound Interest ********");

        System.out.print("Enter amount of principal originally deposited: ");
        double principalAmount = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter annual interest rate paid by account: ");
        double annualInterestRate = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter number of times per year that the interest is compounded: ");
        double compoundedInterest = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter the number of years account will be left to earn interest: ");
        double numberOfYears = Double.parseDouble(scanner.nextLine());

        double amountOfMoney = principalAmount * Math.pow(1 + (annualInterestRate / compoundedInterest), compoundedInterest * numberOfYears);
        System.out.println(amountOfMoney + " will be in account after " + numberOfYears + " years.");
    }


}
