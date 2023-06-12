package carsharing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String dbName;
        if (args.length > 1) {
            System.out.println(args);
            dbName = args[1];
        } else {
            dbName = "test";
        }

        Scanner scanner = new Scanner(System.in);
        Manager manager = new Manager(dbName);


        while(true) {
            manager.displayGeneralOptions();
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 0) break;
            while(true) {
                manager.displayCompanyOptions();
                int managerChoice = Integer.parseInt(scanner.nextLine());
                if (managerChoice == 0) break;

                if (managerChoice == 1) manager.listCompanies();
                if (managerChoice == 2) manager.createCompany();
            }
        }

    }
}

