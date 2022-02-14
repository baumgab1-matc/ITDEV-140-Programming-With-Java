import employees.Employee;
import employees.ProductWorker;
import employees.ShiftSupervisor;
import employees.TeamLeader;
import encryption.EncryptionController;
import ships.CargoShip;
import ships.CruiseShip;
import ships.Ship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //chapter 10
        //challenge 1
//        Employee employee = new Employee("Brent", "453-M", "3-4-2020");
//        System.out.println(employee);
//
//        ProductWorker worker = new ProductWorker("Tyler", "555-L", "4-5-2015", 1, 19.44);
//        System.out.println(worker);
//
//
////        //challenge 2
//        ShiftSupervisor shiftSupervisor = new ShiftSupervisor("Peter", "334-A", "4-5-2019");
//        System.out.println(shiftSupervisor);
//
//        shiftSupervisor.setAnnualSalary(89000);
//        shiftSupervisor.setAnnualProductionBonus(3000);
//
//        System.out.println(shiftSupervisor);
//
////        //challenge 3
//        TeamLeader teamLeaderOne = new TeamLeader("Rick", "343-B", "5-5-2011", 1, 20, 345, 20, 15);
//        System.out.println(teamLeaderOne);
//
//        TeamLeader teamLeaderTwo = new TeamLeader("Tessa", "234-C", "9-9-2009");
//        teamLeaderTwo.setMonthlyBonus(1000);
//        teamLeaderTwo.setRequiredTrainingHours(50);
//        teamLeaderTwo.setAttendedTrainingHours(30);
//        teamLeaderTwo.setShift(1);
//        teamLeaderTwo.setPayRate(30);
//        System.out.println(teamLeaderTwo);


        //challenge 10
//        challenge10();

        //chapter 11
        //challenge 7 and 8
        //encrypt data
        EncryptionController controller = new EncryptionController(args[0], 5);
//        controller.encrypt();

        //decrypt data
        controller.decrypt();

    }

    private static void challenge10() {
        System.out.println("*** Chapter 10 Challenge 10: Ship, CruiseShip, CargoShip Classes ***");

        //make ships
        Ship titanic = new Ship("Titanic", "1909");
        Ship mayFlower = new Ship("Mayflower", "1620");
        Ship sevenSeas = new CruiseShip("Seven Seas Explorer", "2015", 738);
        Ship vikingSun = new CruiseShip("Viking Sun", "2010", 930);
        Ship ssArgus = new CargoShip("SS Argus", "1913", 100);
        Ship ssFlorida = new CargoShip("SS Florida", "1889", 50);

        List<Ship> ships = Arrays.asList(titanic, mayFlower, sevenSeas, vikingSun, ssArgus, ssFlorida);

        ships.forEach(System.out::println);
    }

}
