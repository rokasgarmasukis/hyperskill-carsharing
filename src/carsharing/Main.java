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

        CarSharingRepository carSharingRepository = new CarSharingRepository(dbName);
        Scanner scanner = new Scanner(System.in);
        ManagerConsole managerConsole = new ManagerConsole(carSharingRepository);
        CustomerConsole customer = new CustomerConsole(carSharingRepository);


        menu1:
        while(true) {
            // 1 - login
            // 0 - exit
            displayLoginOptions();
            int choice = Integer.parseInt(scanner.nextLine());
            // exit if 0
            if (choice == 0) break;
            // login if 1
            if (choice == 1) {
                menuManager2:
                while(true) {
                    // 1. Company list
                    // 2. Create a company
                    // 0. Back
                    managerConsole.displayGeneralOptions();
                    int generalChoice = Integer.parseInt(scanner.nextLine());

                    if (generalChoice == 0) break;

                    if (generalChoice == 2) managerConsole.createCompany();


                    if (generalChoice == 1) { // show company list
                        menuManager3:
                        while (true) {
                            // Choose company
                            // 1
                            // 2
                            // ...
                            // 0. Back
                            boolean companiesExist = managerConsole.listCompanies();
                            if (!companiesExist) break;

                            int companyChoice = Integer.parseInt(scanner.nextLine());

                            if (companyChoice == 0) break;

                            // check if company exists...
                            menu4:
                            while (true) {
                                // <Company Name> company:
                                // 1. Car list
                                // 2. Create a car
                                // 0. Back
                                managerConsole.displayCompanyOptions(companyChoice);
                                int companyOptions = Integer.parseInt(scanner.nextLine());

                                if (companyOptions == 0) break menuManager3;

                                if (companyOptions == 1) managerConsole.listCarsForCompany(companyChoice);

                                if (companyOptions == 2) managerConsole.createCarForCompany(companyChoice);

                            }
                        }
                    }
                }
            }
            if (choice == 2) {
                menuCustomer2:
                while(true) {

                }
            }
            if (choice == 3) {

            }

        }

    }

    public static void displayLoginOptions() {
        System.out.println();
        System.out.println("1. Log in as a manager");
        System.out.println("0. Exit");
    }
}

