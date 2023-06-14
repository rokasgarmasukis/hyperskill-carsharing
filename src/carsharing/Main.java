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
        CustomerConsole customerConsole = new CustomerConsole(carSharingRepository);


        menu1:
        while(true) {
            // 1. Log in as a manager
            // 2. Log in as a customer
            // 3. Create a customer
            // 0. Exit
            displayLoginOptions();
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 0) break;

            if (choice == 3) customerConsole.createCustomer();

            // MANAGER Console
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

            // CUSTOMER Console
            if (choice == 2) {
                menuCustomer2:
                while(true) {
                    boolean customersExist = customerConsole.listCustomers();
                    if (!customersExist) break;

                    int customerId = Integer.parseInt(scanner.nextLine());
                    if (customerId == 0) break;
                    while (true) {
                        // 1. Rent a car
                        // 2. Return a rented car
                        // 3. My rented car
                        // 0. Back
                        customerConsole.displayCustomerOptions();
                        int carRentingChoice = Integer.parseInt(scanner.nextLine());
                        if (carRentingChoice == 0) break;
                        if (carRentingChoice == 3) {
                            customerConsole.displayRentedCar(customerId);
                            continue;
                        }
                        if (carRentingChoice == 1) {
                            boolean customerHasCarAlready = customerConsole.hasCar(customerId);
                            if (customerHasCarAlready) continue;

                            boolean companiesExist = customerConsole.listCompanies();
                            if (!companiesExist) continue;
                            int companyChoice = Integer.parseInt(scanner.nextLine());

                            boolean carsExist = customerConsole.listCarsForCompany(companyChoice);
                            if (!carsExist) continue;
                            int carChoice = Integer.parseInt(scanner.nextLine());
                            customerConsole.rentCar(customerId, carChoice);
                            continue;
                        }
                        if (carRentingChoice == 2) customerConsole.returnCar(customerId);
                    }

                }
            }

        }

    }

    public static void displayLoginOptions() {
        System.out.println();
        System.out.println("1. Log in as a manager");
        System.out.println("2. Log in as a customer");
        System.out.println("3. Create a customer");
        System.out.println("0. Exit");
    }
}

