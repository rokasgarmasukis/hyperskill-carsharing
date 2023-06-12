package carsharing;

import carsharing.entities.Company;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Manager {

    public CarSharingRepository carSharingRepository;;

    public Manager(String dbName) {
        carSharingRepository = new CarSharingRepository(dbName);
    }
    public void displayCompanyOptions() {
        System.out.println();
        System.out.println("1. Company list");
        System.out.println("2. Create a company");
        System.out.println("0. Back");
    }


    public void displayGeneralOptions() {
        System.out.println();
        System.out.println("1. Log in as a manager");
        System.out.println("0. Exit");
    }


    public void listCompanies() {

        List<Company> companies = carSharingRepository.getCompanies();
//        List<Company> companies = new ArrayList<>();
        if (companies.size() == 0) {
            System.out.println();
            System.out.println("The company list is empty!");
            return;
        }
        System.out.println();
        System.out.println("Company list:");
        companies.forEach(System.out::println);
    }

    public void createCompany() {
        System.out.println();
        System.out.println("Enter the company name:");
        Scanner scan = new Scanner(System.in);
        String name = scan.nextLine();
        carSharingRepository.addCompany(name);
        System.out.println("The company was created!");
    }
}
