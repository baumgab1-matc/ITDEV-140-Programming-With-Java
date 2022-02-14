package employees;

import employees.exceptions.InvalidPayRate;
import employees.exceptions.InvalidShift;

//challenge 1
public class ProductWorker extends Employee {

    private int shift;
    private double payRate;

    public ProductWorker(String name, String employeeNumber, String hireDate) {
        super(name, employeeNumber, hireDate);
    }

    public ProductWorker(String name, String employeeNumber, String hireDate, int shift, double payRate) {
        super(name, employeeNumber, hireDate);

        if (shift != 1 && shift != 2) {
            throw new InvalidShift();
        }

        if (payRate < 0) {
            throw new InvalidPayRate();
        }

        this.shift = shift;
        this.payRate = payRate;
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {

        if (shift != 1 && shift != 2) {
            throw new InvalidShift();
        }

        this.shift = shift;
    }

    public double getPayRate() {
        return payRate;
    }

    public void setPayRate(double payRate) {
        if (payRate < 0) {
            throw new InvalidPayRate();
        }

        this.payRate = payRate;
    }

    @Override
    public String toString() {
        return  super.toString() +
                ", shift='" + shift + '\'' +
                ", payRate='" + payRate + '\'';
    }
}
