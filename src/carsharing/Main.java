package carsharing;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String dbName;
        if (args.length > 1) {
            dbName = args[1];
        } else {
            dbName = "test";
        }

        Scanner scanner = new Scanner(System.in);
        Manager manager = new Manager(dbName);


        menu1:
        while(true) {
            // 1 - login
            // 0 - exit
            manager.displayLoginOptions();
            int choice = Integer.parseInt(scanner.nextLine());
            // exit if 0
            if (choice == 0) break;
            // login if 1
            menu2:
            while(true) {
                // 1. Company list
                // 2. Create a company
                // 0. Back
                manager.displayGeneralOptions();
                int generalChoice = Integer.parseInt(scanner.nextLine());

                if (generalChoice == 0) break;

                if (generalChoice == 2) manager.createCompany();


                if (generalChoice == 1) { // show company list
                    menu3:
                    while (true) {
                        // Choose company
                        // 1
                        // 2
                        // ...
                        // 0. Back
                        boolean emptyList = manager.listCompanies();
                        if (emptyList) break;

                        int companyChoice = Integer.parseInt(scanner.nextLine());

                        if (companyChoice == 0) break;

                        // check if company exists...
                        menu4:
                        while (true) {
                            // <Company Name> company:
                            // 1. Car list
                            // 2. Create a car
                            // 0. Back
                            manager.displayCompanyOptions(companyChoice);
                            int companyOptions = Integer.parseInt(scanner.nextLine());

                            if (companyOptions == 0) break menu3;

                            if (companyOptions == 1) manager.listCarsForCompany(companyChoice);

                            if (companyOptions == 2) manager.createCarForCompany(companyChoice);

                        }
                    }
                }
            }
        }

    }
}

