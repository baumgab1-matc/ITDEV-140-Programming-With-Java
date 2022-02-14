package chapterSevenChallengeNineteen;

public class Player {

    private int correctAnswers;

    public Player() {
    }

    public void correctAnswer() {
        correctAnswers++;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

}
