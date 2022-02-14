package crud;

import java.util.Optional;
import java.util.Scanner;

public class StudentController {

    private final Scanner scanner;

    public StudentController(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        boolean isRunning = true;
        String menu = "1 - Create Student\n" +
                      "2 - Read Student\n" +
                      "3 - Update Student\n" +
                      "4 - Delete Student";


        while (isRunning) {
            System.out.println(menu);
            System.out.print("> ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addStudent();
                    break;

                case "2":
                    fetchStudent();
                    break;

                case "3":
                    updateStudent();
                    break;

                case "4":
                    deleteStudent();
                    break;

                case "5":
                    isRunning = false;
                    break;

                default:
                    System.out.println("\nInvalid choice! Try again.\n");
            }
        }
    }

    //CREATE OPERATION
    public void addStudent() {
        System.out.print("\nEnter student first name: ");
        String firstName = scanner.nextLine().trim();

        System.out.print("Enter student last name: ");
        String lastName = scanner.nextLine().trim();

        System.out.print("Enter student id: ");
        String studentId = scanner.nextLine().trim();

        Student student = new Student(firstName, lastName, studentId);

        DatabaseOperations.getInstance().insertStudent(student);

        System.out.println("\nStudent created!\n");
    }

    //helper method
    private Optional<Student> fetch() {
        System.out.print("\nEnter student's id: ");
        String studentId = scanner.nextLine();

        return DatabaseOperations.getInstance().fetchStudentById(studentId);
    }

    //READ OPERATION
    public void fetchStudent() {
        Optional<Student> foundStudent = fetch();

        if (foundStudent.isPresent()) {
            System.out.println("Student found: " + foundStudent.get() + "\n");
        } else {
            System.out.println("\nNo student found.\n");
        }
    }


    //UPDATE OPERATION
    public void updateStudent() {
        Optional<Student> foundStudent = fetch();

        if (foundStudent.isEmpty()) {
            System.out.println("\nNo student found.\n");
            return;
        }

        Student studentToUpdate = foundStudent.get();
        System.out.println("Student found: " + studentToUpdate);

        System.out.print("Updated student's first name (hit 'enter' to keep same): ");
        String updatedFirstName = scanner.nextLine().trim();

        System.out.print("Updated student's last name (hit 'enter' to keep same:) ");
        String updatedLastName = scanner.nextLine().trim();

        if (!updatedFirstName.isEmpty()) {
            studentToUpdate.setStudentFirstName(updatedFirstName);
        }

        if (!updatedLastName.isEmpty()) {
            studentToUpdate.setStudentLastName(updatedLastName);
        }

        DatabaseOperations.getInstance().updateStudent(studentToUpdate);
        System.out.println("\nStudent Updated!\n");
    }

    //DELETE OPERATION
    public void deleteStudent() {
        Optional<Student> foundStudent = fetch();

        if (foundStudent.isEmpty()) {
            System.out.println("\nNo student found.\n");
            return;
        }

        Student studentToRemove = foundStudent.get();
        System.out.println("Student found: " + studentToRemove );

        System.out.print("Are you sure you want to delete this student? (yes/no): ");
        String choice = scanner.nextLine().toLowerCase().trim();

        if ("yes".equals(choice) || "y".equals(choice)) {
            DatabaseOperations.getInstance().removeStudent(studentToRemove);
            System.out.println("\nStudent Removed\n");
        } else {
            System.out.println("\nStudent not removed\n");
        }
    }

}
