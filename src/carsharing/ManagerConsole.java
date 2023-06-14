package carsharing;

import carsharing.entities.Car;
import carsharing.entities.Company;

import java.util.List;
import java.util.Scanner;

public class ManagerConsole {

    Scanner scan = new Scanner(System.in);
    private final CarSharingRepository carSharingRepository;

    public ManagerConsole(CarSharingRepository carSharingRepository) {
        this.carSharingRepository = carSharingRepository;
    }
    public void displayGeneralOptions() {
        System.out.println();
        System.out.println("1. Company list");
        System.out.println("2. Create a company");
        System.out.println("0. Back");
    }

    public void displayCompanyOptions(int companyId) {
        Company company = carSharingRepository.getCompany(companyId);
        System.out.println();
        System.out.printf("'%s' company\n", company.getName());
        System.out.println("1. Car list");
        System.out.println("2. Create a car");
        System.out.println("0. Back");
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

    public void createCompany() {
        System.out.println();
        System.out.println("Enter the company name:");
        String name = scan.nextLine();
        carSharingRepository.addCompany(name);
        System.out.println("The company was created!");
    }


    public void listCarsForCompany(int companyChoice) {
        List<Car> cars = carSharingRepository.getCars(companyChoice);

        if (cars.size() == 0) {
            System.out.println();
            System.out.println("The car list is empty!");
            return;
        }
        System.out.println();
        System.out.println("Car list:");
        for (int i = 1; i <= cars.size(); i++) {
            System.out.printf("%d. %s\n", i, cars.get(i - 1).getName());
        }

    }

    public void createCarForCompany(int companyChoice) {
        System.out.println();
        System.out.println("Enter the car name:");
        String name = scan.nextLine();
        carSharingRepository.createCar(name, companyChoice);
        System.out.println("The car was added!");
    }
}
