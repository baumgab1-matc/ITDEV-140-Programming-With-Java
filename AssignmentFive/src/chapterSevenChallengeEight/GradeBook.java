package chapterSevenChallengeEight;

import java.util.Scanner;

public class GradeBook {

    private final Student[] students;
    private final Scanner scanner;

    public GradeBook(Scanner scanner) {
        this.scanner = scanner;
        this.students = new Student[5];
    }

    public void fill() {
        //creates 5 students and adds test scores for each student
        fillGradeBook();

        //prints each students info
        printStudentsInfo();
    }

    //creates 5 students
    private void fillGradeBook() {
        for (int i = 0; i < students.length; i++) {
            System.out.print("Enter student (" + (i + 1) + ") name: ");
            String name = scanner.nextLine();

            //create new student
            Student student = new Student(name);

            //add grades to student
            for (int j = 0; j < student.getGradeCount(); ) {
                System.out.print("Enter test score " + (j + 1) + ": ");
                double grade = Double.parseDouble(scanner.nextLine());

                if (grade < 0 || grade > 100) {
                    System.out.println("Invalid grade given! Try again.");
                    continue;
                }

                student.addGrade(grade);
                j++;
            }

            students[i] = student;
            System.out.println();
        }
    }

    private void printStudentsInfo() {
        for (Student student : students) {
            double average = student.getAverage();
            System.out.printf("%s's average grade is: %.2f\n", student.getName(), average);
            System.out.printf("%s's letter grade is: %s\n\n", student.getName(), getLetterGrade(average));
        }
    }

    private String getLetterGrade(double grade) {
        String letter;

        if (grade < 60) {
            letter = "F";
        } else if (grade < 70) {
            letter = "D";
        } else if (grade < 80) {
            letter = "C";
        } else if (grade < 90) {
            letter = "B";
        } else {
            letter = "A";
        }

        return letter;
    }

}
