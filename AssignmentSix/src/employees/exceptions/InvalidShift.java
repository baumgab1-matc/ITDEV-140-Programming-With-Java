package employees.exceptions;

public class InvalidShift extends RuntimeException {

    public InvalidShift(String errorMessage) {
        super(errorMessage);
    }

    public InvalidShift() {
        super("Invalid shift given. Must be either 1 for day shift or 2 or night shift");
    }

}
