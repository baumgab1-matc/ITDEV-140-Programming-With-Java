package chapterSevenChallengeNineteen;

public class Question {

    private final String question;
    private String[] possibleAnswers;
    private final String correctAnswer;

    public Question(String question, String correctAnswer) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.possibleAnswers = new String[4];
    }

    public void addPossibleAnswers(String[] possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getPossibleAnswers() {
        return possibleAnswers;
    }
}
