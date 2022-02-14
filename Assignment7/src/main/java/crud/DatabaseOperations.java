package crud;

import java.sql.*;
import java.util.Optional;

public class DatabaseOperations {

    private static DatabaseOperations instance;

    private DatabaseOperations() {}

    public static DatabaseOperations getInstance() {
        if (instance == null) {
            instance = new DatabaseOperations();
        }

        return instance;
    }

    //CREATE
    public void insertStudent(Student student) {
        String insertQuery = "INSERT INTO students(FirstName, LastName, StudentID) VALUES(?,?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
            pstmt.setString(1, student.getStudentFirstName());
            pstmt.setString(2, student.getStudentLastName());
            pstmt.setString(3, student.getStudentId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //READ
    public Optional<Student> fetchStudentById(String toSearch) {
        String findQuery = String.format("select * from students where StudentId = '%s'",toSearch);
        Optional<Student> foundStudent = Optional.empty();

        try (Connection conn = connect();
             PreparedStatement pstmt  = conn.prepareStatement(findQuery)){
            ResultSet rs  = pstmt.executeQuery();

            if (rs.next()) {
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String studentId = rs.getString("StudentID");

                foundStudent = Optional.of(new Student(firstName, lastName, studentId));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return foundStudent;
    }

    //UPDATE
    public void updateStudent(Student toUpdate) {
        String updateQuery = String.format("UPDATE students " +
                "SET FirstName = '%s', LastName = '%s' " +
                "WHERE StudentID = '%s'",
                toUpdate.getStudentFirstName(), toUpdate.getStudentLastName(), toUpdate.getStudentId());

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
             pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //DELETE
    public void removeStudent(Student toDelete ) {
        String removeQuery = String.format("DELETE FROM students WHERE StudentID = '%s'", toDelete.getStudentId());

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(removeQuery)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    private Connection connect() {
        String url = "jdbc:mysql://localhost:3306/student_database?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, "root", "password");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
}
