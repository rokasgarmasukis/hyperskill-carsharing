package carsharing;

import carsharing.entities.Car;
import carsharing.entities.Company;
import carsharing.entities.Customer;

import java.util.List;
import java.util.Scanner;

public class CustomerConsole {
    private final CarSharingRepository carSharingRepository;

    public CustomerConsole(CarSharingRepository carSharingRepository) {
        this.carSharingRepository = carSharingRepository;
    }

    public boolean listCustomers() {

        List<Customer> customers = carSharingRepository.getCustomers();

        if (customers.size() == 0) {
            System.out.println();
            System.out.println("The customer list is empty!");
            return false;
        }
        System.out.println();
        customers.forEach(System.out::println);
        System.out.println("O. Back");
        return true;

    }

    public void displayCustomerOptions() {
        System.out.println();
        System.out.println("1. Rent a car");
        System.out.println("2. Return a rented car");
        System.out.println("3. My rented car");
        System.out.println("0. Back");
    }


    public void createCustomer() {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("Enter the customer name:");
        String name = sc.nextLine();
        carSharingRepository.createCustomer(name);
        System.out.println("The customer was added");
    }

    public void displayRentedCar(int customerId) {
        Customer customer = carSharingRepository.getCustomer(customerId);
//        System.out.println(customer.getRentedCarId());
        if (customer.getRentedCarId() == 0) {
            System.out.println();
            System.out.println("You didn't rent a car!");
        } else {
            Car car = carSharingRepository.getCar(customer.getRentedCarId());
            Company company = carSharingRepository.getCompany(car.getCompanyId());
            System.out.println();
            System.out.println("Your rented car:");
            System.out.println(car.getName());
            System.out.println("Company:");
            System.out.println(company.getName());
        }
    }

    public boolean listCompanies() {

        List<Company> companies = carSharingRepository.getCompanies();
        if (companies.size() == 0) {
            System.out.println();
            System.out.println("The company list is empty!");
            return false;
        }
        System.out.println();
        System.out.println("Choose a company:");
        companies.forEach(System.out::println);
        System.out.println("0. Back");
        return true;
    }


    public boolean listCarsForCompany(int companyChoice) {
        List<Car> cars = carSharingRepository.getCars(companyChoice);
        Company company = carSharingRepository.getCompany(companyChoice);

        if (cars.size() == 0) {
            System.out.println();
            System.out.printf("No available cars in the '%s' company!\n", company.getName());
            return false;
        }
        System.out.println();
        System.out.println("Choose a car:");
        for (int i = 1; i <= cars.size(); i++) {
            System.out.printf("%d. %s\n", i, cars.get(i - 1).getName());
        }
        return true;
    }

    public void rentCar(int customerId, int carId) {
        Car car = carSharingRepository.getCar(carId);
        carSharingRepository.updateCarForCustomer(customerId, carId);
        System.out.println();
        System.out.printf("You rented '%s'", car.getName());
    }

    public boolean hasCar(int customerId) {
        Customer customer = carSharingRepository.getCustomer(customerId);
        if (customer.getRentedCarId() != 0) {
            System.out.println();
            System.out.println("You've already rented a car!");
            return true;
        }
        return false;
    }

    public void returnCar(int customerId) {
        Customer customer = carSharingRepository.getCustomer(customerId);
        if (customer.getRentedCarId() == 0) {
            System.out.println();
            System.out.println("You didn't rent a car!");
            return;
        }
        carSharingRepository.updateCarForCustomer(customerId, null);
        System.out.println();
        System.out.println("You've returned a rented car!");
    }
}
