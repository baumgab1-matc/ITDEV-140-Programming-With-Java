package employees.exceptions;

public class InvalidEmployeeNumber extends RuntimeException {

    public InvalidEmployeeNumber(String errorMessage) {
        super(errorMessage);
    }

    public InvalidEmployeeNumber() {
        super("Employee number is not valid. Format must be XXX-L, where X's are digits from 0-9 and L is letter from A-M");
    }

}
