package crud;

import java.util.Scanner;

public class CRUDMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        new StudentController(scanner).run();
    }

}
