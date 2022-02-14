package chapterSevenChallengeNineteen;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Trivia {

    private final Scanner scanner;
    private final List<Question> questions;
    private final Player playerOne;
    private final Player playerTwo;


    public Trivia(Scanner scanner) {
        this.scanner = scanner;
        this.playerOne = new Player();
        this.playerTwo = new Player();
        this.questions = new ArrayList<>();
    }

    public void play() {
        addQuestions();
        int round = 1;

        for (int i = 0; i < questions.size() - 1; i+=2) {
            System.out.printf("***** ROUND %d *****\n", round++);

            //ask player one a question
            System.out.println("Player one's turn: ");
            askQuestionTo(playerOne, i);

            //ask player two a question
            System.out.println("Player two's turn: ");
            askQuestionTo(playerTwo, i + 1);
        }

        //questions over, see who won
        if (playerOne.getCorrectAnswers() == playerTwo.getCorrectAnswers()) {
            System.out.printf("Tied game! %d to %d", playerOne.getCorrectAnswers(), playerTwo.getCorrectAnswers());
        } else if (playerOne.getCorrectAnswers() > playerTwo.getCorrectAnswers()) {
            System.out.printf("Player one wins! %d to %d", playerOne.getCorrectAnswers(), playerTwo.getCorrectAnswers());
        } else {
            System.out.printf("Player two wins! %d to %d", playerTwo.getCorrectAnswers(), playerOne.getCorrectAnswers());
        }
    }

    private void askQuestionTo(Player player, int idx) {
        Question question = questions.get(idx);
        //print out question
        System.out.println(question.getQuestion());

        //print out possible answers
        char letter = 'a';
        for (String possibleAnswer : question.getPossibleAnswers()) {
            System.out.println(letter++ + " - " + possibleAnswer);
        }

        System.out.print("Your answer: ");
        String answerGiven = scanner.nextLine().toLowerCase().trim();

        if (answerGiven.equals(question.getCorrectAnswer())) {
            System.out.println("You're correct!!\n");
            player.correctAnswer();
        } else {
            System.out.println("Sorry incorrect. Correct answer was '" + question.getCorrectAnswer() + "'.\n");
        }
    }

    private void addQuestions() {
        Question question1 = new Question("If you freeze water, what do you get?", "a");
        question1.addPossibleAnswers(new String[] {"ice", "fire", "gas", "air"});
        questions.add(question1);

        Question question2 = new Question("What is the name of the fairy in Peter Pan?", "b");
        question2.addPossibleAnswers(new String[] {"Wendy Darling", "Tinker Bell", "Nibs", "Curly"});
        questions.add(question2);

        Question question3 = new Question( "Where is the Great Pyramid of Giza?", "d");
        question3.addPossibleAnswers(new String[] {"Algeria", "Libya", "Sudan", "Egypt"});
        questions.add(question3);

        Question question4 = new Question("How many days are in a year?", "c");
        question4.addPossibleAnswers(new String[] {"364", "366", "365", "390"});
        questions.add(question4);

        Question question5 = new Question("What is a group of lions called?", "c");
        question5.addPossibleAnswers(new String[] {"Swarm", "Murder", "Pride", "Gang"});
        questions.add(question5);

        Question question6 = new Question("What does a Scoville unit measure?", "a");
        question6.addPossibleAnswers(new String[] {"Spiciness", "Coldness", "Brightness", "Loudness"});
        questions.add(question6);

        Question question7 = new Question("Galileo was the citizen of which country?", "d");
        question7.addPossibleAnswers(new String[] {"France", "Spain", "Portugal", "Italy"});
        questions.add(question7);

        Question question8 = new Question(" In ancient Greece, throwing an apple at someone was a declaration of what?", "b");
        question8.addPossibleAnswers(new String[] {"War", "Love", "Friendship", "Hate"});
        questions.add(question8);

        Question question9 = new Question("What country has the most vending machines per capita?", "d");
        question9.addPossibleAnswers(new String[] {"USA", "China", "Australia", "Japan"});
        questions.add(question9);

        Question question10 = new Question("Carrots are a good source of which vitamin?", "a");
        question10.addPossibleAnswers(new String[] {"Vitamin A", "Vitamin B", "Vitamin C", "Vitamin D"});
        questions.add(question10);
    }

}
