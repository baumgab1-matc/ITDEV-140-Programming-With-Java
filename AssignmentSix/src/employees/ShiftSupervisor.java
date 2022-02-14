package employees;

import employees.exceptions.InvalidPayRate;

//challenge 2
public class ShiftSupervisor extends Employee {

    private double annualSalary;
    private double annualProductionBonus;

    public ShiftSupervisor(String name, String employeeNumber, String hireDate) {
        super(name, employeeNumber, hireDate);
    }

    public ShiftSupervisor(String name, String employeeNumber, String hireDate, double annualSalary, double annualProductionBonus) {
        super(name, employeeNumber, hireDate);

        if (annualSalary < 0 || annualProductionBonus < 0) {
            throw new InvalidPayRate();
        }

        this.annualSalary = annualSalary;
        this.annualProductionBonus = annualProductionBonus;
    }

    public double getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(double annualSalary) {
        if (annualSalary < 0) {
            throw new InvalidPayRate();
        }

        this.annualSalary = annualSalary;
    }

    public double getAnnualProductionBonus() {
        return annualProductionBonus;
    }

    public void setAnnualProductionBonus(double annualProductionBonus) {
        if (annualProductionBonus < 0) {
            throw new InvalidPayRate();
        }

        this.annualProductionBonus = annualProductionBonus;
    }

    @Override
    public String toString() {
        return super.toString() +
                "annualSalary='" + annualSalary +'\'' +
                ", annualProductionBonus='" + annualProductionBonus + '\'';
    }
}
