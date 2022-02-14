import chapterSixChallenge19.FishingGame;
import chapterSixChallengeOne.Employee;
import chapterSixChallengeSix.TestScores;

import java.lang.reflect.Field;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    public static void main(String[] args) {
        //chapter 5
        //challenge 10
//        double profit = challenge10();
//        System.out.println(profit > 0 ? "Your profit is: " + profit : "Your loss is: " + profit);
        
        //challenge 16
//        double presentValue = challengeSixteen();
//        System.out.printf("You need to deposit %.2f dollars.", presentValue);

        //challenge 17
//        challengeSeventeen();

        //chapter 6
        //challenge 1
//        Employee susanMeyers = new Employee("Susan Meyers", 47899, "Accounting", "Vice President");
//
//        Employee markJones = new Employee("Mark Jones", 39119);
//        markJones.setDepartment("IT");
//        markJones.setPosition("Programmer");
//
//        Employee joyRogers = new Employee();
//        joyRogers.setName("Joy Rogers");
//        joyRogers.setIdNumber(81774);
//        joyRogers.setDepartment("Manufacturing");
//        joyRogers.setPosition("Engineer");
//
//        System.out.println(susanMeyers + "\n" + markJones + "\n" + joyRogers);


        //challenge 6
//        System.out.print("Enter test score 1: ");
//        double scoreOne = Double.parseDouble(scanner.nextLine());
//
//        System.out.print("Enter test score 2: ");
//        double scoreTwo = Double.parseDouble(scanner.nextLine());
//
//        System.out.print("Enter test score 3: ");
//        double scoreThree = Double.parseDouble(scanner.nextLine());
//
//        TestScores scores = new TestScores(scoreOne, scoreTwo, scoreThree);
//        System.out.printf("The average of the three tests is: %.2f", scores.getAverage());

        //challenge 19
        FishingGame fishingGame = new FishingGame();
        fishingGame.play();

    }

    private static double challenge10() {
        System.out.println("*** Chapter 5 Challenge 10: Stock Profit ***");

        System.out.print("Enter number of shares: ");
        double NS = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter the purchase price per share: ");
        double PP = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter the amount of purchase commission paid: ");
        double PC = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter sale price per share: ");
        double SP = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter the amount of sale commission: ");
        double SC = Double.parseDouble(scanner.nextLine());

        return ((NS * SP) - SC) - ((NS * PP) + PC);
    }
    
    private static double challengeSixteen() {
        System.out.println("*** Chapter 5 Challenge 16: Present Year ***");

        System.out.print("Enter future amount you want in account: ");
        double futureAmount = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter annual interest rate: ");
        double interestRate = Double.parseDouble(scanner.nextLine()) / 100;

        System.out.print("Enter number of years you plan to let the money sit: ");
        double years = Double.parseDouble(scanner.nextLine());
        
        double denominator = Math.pow(1 + interestRate, years);

        return futureAmount / denominator;
    }

    private static void challengeSeventeen() {
        System.out.println("*** Chapter 5 Challenge 17: Rock, Paper, Scissors Game ***");

        boolean isPlaying = true;

        while (isPlaying) {
            //let player choose their move
            String playerMove = getPlayerMove();

            //let computer choose its move
            String computerMove = getComputerMove();
            System.out.printf("Computer plays '%s'\n", computerMove);

            //check for tie
            if (playerMove.equals(computerMove)) {
                System.out.println("Tied game! Try again\n");
                continue;
            }

            //no tie happened, display winner and break loop
            boolean humanWins = isWinner(playerMove, computerMove);

            //get reason why human or computer won and print it
            String winningReason = humanWins ? getWinningReason(playerMove, computerMove) : getWinningReason(computerMove, playerMove);
            System.out.print(winningReason);

            System.out.println(humanWins ? " You Win!!" : " You lose!!");
            isPlaying = false;
        }
    }

    private static String getPlayerMove() {
        String move;

        //loop till user enters valid move
        while (true) {
            System.out.print("Enter move 'rock', 'paper', 'scissors', 'lizard' or 'spock': ");
            move = scanner.nextLine().toLowerCase().trim();

            //check if move is valid, if not just continue looping
            if (!"rock".equals(move) && !"paper".equals(move) && !"scissors".equals(move) && !"lizard".equals(move) && !"spock".equals(move)) {
                System.out.println("Invalid move given! Try again!");
                continue;
            }

            break;
        }

        return move;
    }

    private static String getComputerMove() {
        int num = random.nextInt(5) + 1;
        String computerMove;

        if (num == 1) {
            computerMove = "rock";
        } else if (num == 2) {
            computerMove = "paper";
        } else if (num == 3){
            computerMove = "scissors";
        } else if (num == 4) {
            computerMove = "lizard";
        } else {
            computerMove = "spock";
        }

        return computerMove;
    }

    private static boolean isWinner(String currentPlayer, String otherPlayer) {
        return currentPlayer.equals("scissors") && otherPlayer.equals("paper") ||
                currentPlayer.equals("paper") && otherPlayer.equals("rock") ||
                currentPlayer.equals("rock") && otherPlayer.equals("lizard") ||
                currentPlayer.equals("lizard") && otherPlayer.equals("spock") ||
                currentPlayer.equals("spock") && otherPlayer.equals("scissors") ||
                currentPlayer.equals("scissors") && otherPlayer.equals("lizard") ||
                currentPlayer.equals("lizard") && otherPlayer.equals("paper") ||
                currentPlayer.equals("paper") && otherPlayer.equals("spock") ||
                currentPlayer.equals("spock") && otherPlayer.equals("rock") ||
                currentPlayer.equals("rock") && otherPlayer.equals("scissors");
    }

    private static String getWinningReason(String winningMove, String losingMove) {
        String reason;

        if (winningMove.equals("scissors") && losingMove.equals("paper")) {
            reason = "Scissors cuts paper!";
        } else if (winningMove.equals("paper") && losingMove.equals("rock")) {
            reason = "Paper covers rock!";
        } else if (winningMove.equals("rock") && losingMove.equals("lizard")) {
            reason = "Rock crushes lizard!";
        } else if (winningMove.equals("lizard") && losingMove.equals("spock")) {
            reason = "Lizard poisons spock!";
        } else if (winningMove.equals("spock") && losingMove.equals("scissors")) {
            reason = "Spock smashes scissors!";
        } else if (winningMove.equals("scissors") && losingMove.equals("lizard")) {
            reason = "Scissors decapitates lizard!";
        } else if (winningMove.equals("lizard") && losingMove.equals("paper")) {
            reason = "Lizard eats paper!";
        } else if (winningMove.equals("paper") && losingMove.equals("spock")) {
            reason = "Paper disproves Spock!";
        } else if (winningMove.equals("spock") && losingMove.equals("rock")) {
            reason = "Spock vaporizes rock!";
        } else {
            reason = "Rock crushes scissors";
        }

        return reason;
    }


}
