package employees.exceptions;

public class InvalidPayRate extends RuntimeException {

    public InvalidPayRate(String errorMessage) {
        super(errorMessage);
    }

    public InvalidPayRate() {
        super("Invalid pay rate given. Cannot be negative.");
    }

}
