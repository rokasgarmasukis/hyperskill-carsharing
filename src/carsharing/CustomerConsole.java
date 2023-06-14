package carsharing;

import carsharing.entities.Company;
import carsharing.entities.Customer;

import java.util.List;

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


    public void createCustomer() {

    }
}
