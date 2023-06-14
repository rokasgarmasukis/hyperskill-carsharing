package carsharing.entities;

import java.net.Inet4Address;

public class Customer {
    private int id;
    private String name;
    private Integer rentedCarId = null;

    public Customer(int id, String name, int rentedCarId) {
        this.id = id;
        this.name = name;
        this.rentedCarId = rentedCarId;
    }

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Customer() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRentedCarId() {
        return rentedCarId;
    }

    public void setRentedCarId(int rentedCarId) {
        this.rentedCarId = rentedCarId;
    }

    @Override
    public String toString() {
        return id + ". " + name;
    }
}
