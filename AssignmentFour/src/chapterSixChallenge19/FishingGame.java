package chapterSixChallenge19;

import java.util.Scanner;

public class FishingGame {

    private final Die die;
    private final Player player;
    private final Scanner scanner;

    public FishingGame() {
        this.die = new Die(6);
        this.player = new Player();
        this.scanner = new Scanner(System.in);
    }

    public void play() {
        boolean isPlaying = true;
        boolean isFirstRound = true;

        System.out.println("Welcome to fishing game simulation!");
        System.out.println("You will make series of casts, depending on your catch you will get a certain");
        System.out.println("amount of points from 1-10, 10 being the best. Once you're done playing, the points are added up");
        System.out.println("and your fishing level is given!");

        while (isPlaying) {
            //make player play at least one round
            if (isFirstRound) {
                catchFish();
                isFirstRound = false;
                continue;
            }

            //first round over, check if user wants to continue
            System.out.print("\nWould you like to continue playing? (yes/no) ");
            String choice = scanner.nextLine().toLowerCase().trim();

            if ("no".equals(choice)) {
                displayResults();
                isPlaying = false;
            } else {
                catchFish();
            }
        }
    }

    private void catchFish() {
        int rollNumber = die.roll();
        String message = "";
        int pointsEarned = 0;

        System.out.println("\nMaking cast....");

        switch (rollNumber) {
            case 1:
                message = "You caught a huge fish!";
                pointsEarned = 10;
                break;

            case 2:
                message = "You caught an old shoe!";
                pointsEarned = 1;
                break;

            case 3:
                message = "You caught a little fish!";
                pointsEarned = 4;
                break;

            case 4:
                message = "You didn't catch anything!";
                pointsEarned = 0;
                break;

            case 5:
                message = "You caught a decent sized fish!";
                pointsEarned = 6;
                break;

            case 6:
                message = "You caught a big fish!";
                pointsEarned = 8;
                break;
        }

        System.out.println(message + "\nPoints earned " + pointsEarned);
        player.setPoints(pointsEarned + player.getPoints());
    }


    private void displayResults() {
        System.out.println("\nFishing Over!!");
        System.out.println("Total points earned: " + player.getPoints());
        System.out.println("Your fishing level is: " + getStanding(player.getPoints()));
    }

    private String getStanding(int score) {
        if (score < 50) {
            return "Novice";
        } else if (score < 70) {
            return "Skilled";
        } else if (score < 100) {
            return "Seasoned";
        } else {
            return "Expert";
        }
    }
}
