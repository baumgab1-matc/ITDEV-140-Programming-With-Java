package employees;

import employees.exceptions.InvalidEmployeeNumber;

public class Employee {

    private String name;
    private String employeeNumber;
    private String hireDate;

    public Employee(String name, String employeeNumber, String hireDate) {
        this.name = name;

        //validate number, needs to be XXX-L, X is digit from 0-9 and L is letter from A-M
        if (!isValidNumber(employeeNumber)) {
            throw new InvalidEmployeeNumber();
        }

        this.employeeNumber = employeeNumber;
        this.hireDate = hireDate;
    }

    private boolean isValidNumber(String employeeNumber) {
        //check for format XXX-L
        if (employeeNumber.length() != 5 && employeeNumber.charAt(3) != '-') {
            return false;
        }

        //at this point we have an employee number with length 5 and with a dash, we can safely split it
        String[] holder = employeeNumber.split("-");
        String digits = holder[0];
        String letter = holder[1];

        return digits.matches("[0-9]{3}") && letter.matches("[A-M]");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public String toString() {
        return  this.getClass().getSimpleName() + ": " +
                "name='" + name + '\'' +
                ", employeeNumber='" + employeeNumber + '\'' +
                ", hireDate='" + hireDate + '\'';
    }
}
