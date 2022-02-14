package chapterSixChallengeSix;

public class TestScores {

    private double scoreOne;
    private double scoreTwo;
    private double scoreThree;

    public TestScores(double scoreOne, double scoreTwo, double scoreThree) {
        this.scoreOne = scoreOne;
        this.scoreTwo = scoreTwo;
        this.scoreThree = scoreThree;
    }

    public double getAverage() {
        return (scoreOne + scoreTwo + scoreThree) / 3;
    }

    public double getScoreOne() {
        return scoreOne;
    }

    public void setScoreOne(double scoreOne) {
        this.scoreOne = scoreOne;
    }

    public double getScoreTwo() {
        return scoreTwo;
    }

    public void setScoreTwo(double scoreTwo) {
        this.scoreTwo = scoreTwo;
    }

    public double getScoreThree() {
        return scoreThree;
    }

    public void setScoreThree(double scoreThree) {
        this.scoreThree = scoreThree;
    }
}
