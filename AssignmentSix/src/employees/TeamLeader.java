package employees;

import employees.exceptions.InvalidPayRate;

public class TeamLeader extends ProductWorker {

    private double monthlyBonus;
    private double requiredTrainingHours;
    private double attendedTrainingHours;

    public TeamLeader(String name, String employeeNumber, String hireDate) {
        super(name, employeeNumber, hireDate);
    }

    public TeamLeader(String name, String employeeNumber, String hireDate, int shift, double payRate) {
        super(name, employeeNumber, hireDate, shift, payRate);
    }

    public TeamLeader(String name, String employeeNumber, String hireDate, double monthlyBonus, double requiredTrainingHours, double attendedTrainingHours) {
        super(name, employeeNumber, hireDate);

        if (monthlyBonus < 0) {
            throw new InvalidPayRate();
        }

        this.monthlyBonus = monthlyBonus;
        this.requiredTrainingHours = requiredTrainingHours;
        this.attendedTrainingHours = attendedTrainingHours;
    }

    public TeamLeader(String name, String employeeNumber, String hireDate, int shift, double payRate, double monthlyBonus, double requiredTrainingHours, double attendedTrainingHours) {
        super(name, employeeNumber, hireDate, shift, payRate);

        if (monthlyBonus < 0) {
            throw new InvalidPayRate();
        }

        this.monthlyBonus = monthlyBonus;
        this.requiredTrainingHours = requiredTrainingHours;
        this.attendedTrainingHours = attendedTrainingHours;
    }


    public double getMonthlyBonus() {
        return monthlyBonus;
    }

    public void setMonthlyBonus(double monthlyBonus) {
        if (monthlyBonus < 0) {
            throw new InvalidPayRate();
        }

        this.monthlyBonus = monthlyBonus;
    }

    public double getRequiredTrainingHours() {
        return requiredTrainingHours;
    }

    public void setRequiredTrainingHours(double requiredTrainingHours) {
        this.requiredTrainingHours = requiredTrainingHours;
    }

    public double getAttendedTrainingHours() {
        return attendedTrainingHours;
    }

    public void setAttendedTrainingHours(double attendedTrainingHours) {
        this.attendedTrainingHours = attendedTrainingHours;
    }

    @Override
    public String toString() {
        return super.toString()  +
                "monthlyBonus='" + monthlyBonus + '\'' +
                ", requiredTrainingHours='" + requiredTrainingHours + '\'' +
                ", attendedTrainingHours='" + attendedTrainingHours + '\'';
    }
}
