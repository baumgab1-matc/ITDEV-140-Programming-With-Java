package crud;

public class Student {

    private String studentFirstName;
    private String studentLastName;
    private String studentId;

    public Student(String studentFirstName, String studentLastName, String studentId) {
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.studentId = studentId;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentFirstName='" + studentFirstName + '\'' +
                ", studentLastName='" + studentLastName + '\'' +
                ", studentId='" + studentId + '\'' +
                '}';
    }
}
