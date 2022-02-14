package chapterSevenChallengeEight;

import java.util.Arrays;

public class Student {

    private final String name;
    private final double[] grades;

    public Student(String name) {
        this.name = name;
        this.grades = new double[4];

        Arrays.fill(grades, -1);
    }

    public void addGrade(double score) {
        //add score to first available spot
        for (int i = 0; i < grades.length; i++) {
            if (grades[i] == -1) {
                grades[i] = score;
                break;
            }
        }
    }

    public double getAverage() {
        double sum = 0;
        int size = this.grades.length;

        for (double grade : grades) {
            sum += grade;
        }

        return size != 0 ? sum / size : -1;
    }

    public int getGradeCount() {
        return grades.length;
    }

    public String getName() {
        return name;
    }
}
