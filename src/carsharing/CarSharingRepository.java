package carsharing;

import carsharing.entities.Car;
import carsharing.entities.Company;
import carsharing.entities.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarSharingRepository {

    Connection connection;
    public CarSharingRepository(String dbName) {
        // init db;
        try {
            Class.forName("org.h2.Driver");

            String dbUrl = "jdbc:h2:./src/carsharing/db/" + dbName;

//            System.out.println("Connection to the db...");
            connection = DriverManager.getConnection(dbUrl);
            connection.setAutoCommit(true);
//            System.out.println("Connected successfully");

            Statement stmt = connection.createStatement();
            String sql;
//            sql = "DROP TABLE IF EXISTS customer";
//            stmt.execute(sql);
//            sql = "DROP TABLE IF EXISTS car";
//            stmt.execute(sql);
//            sql = "DROP TABLE IF EXISTS COMPANY";
//            stmt.execute(sql);



//            System.out.println("Table dropped if exists");


            sql = "CREATE TABLE IF NOT EXISTS company (id int identity, name varchar unique not null)";
            stmt.execute(sql);
//            System.out.println("New table created");

            sql = "ALTER TABLE company ALTER COLUMN id RESTART WITH 1";
            stmt.execute(sql);

            sql = "create table if not exists car (" +
                    "id int identity, " +
                    "name varchar unique not null, " +
                    "company_id int not null," +
                    "foreign key (company_id) references company(id)" +
                    ")";

            stmt.execute(sql);

            sql = "create table if not exists customer (" +
                    "id int identity, " +
                    "name varchar unique not null, " +
                    "rented_car_id int," +
                    "foreign key (rented_car_id) references car(id)" +
                    ")";

            stmt.execute(sql);

//          connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Company> getCompanies() {
        List<Company> companies = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            String sql = "select id, name from company";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                companies.add(new Company(rs.getInt("id"), rs.getString("name")));
            }

            rs.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return companies;
    }

    public Company getCompany(int id) {

        Company company = new Company();
        try {
            Statement stmt = connection.createStatement();

            String sql = String.format("select id, name from company where id = %d", id);

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                company.setId(rs.getInt("id"));
                company.setName(rs.getString("name"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return company;
    }

    public void addCompany(String name) {

        try {
            Statement stmt = connection.createStatement();

            String sql = String.format("insert into company (name) values ('%s')", name);

            stmt.execute(sql);
            stmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public List<Car> getCars(int companyId) {
        List<Car> cars = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            String sql = String.format("select id, name, company_id from car where company_id = %d", companyId);
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                cars.add(new Car(rs.getInt("id"), rs.getString("name"), rs.getInt("company_id")));
            }

            rs.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return cars;
    }

    public void createCar(String name, int companyId) {
        try {
            Statement stmt = connection.createStatement();



            String sql = String.format("insert into car (name, company_id) values ('%s', %d)", name, companyId);
            stmt.execute(sql);
            stmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            String sql = "select id, name, rented_car_id from customer";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                customers.add(new Customer(rs.getInt("id"), rs.getString("name"), rs.getInt("rented_car_id")));
            }

            rs.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return customers;
    }

    public Customer getCustomer(int customerId) {
        Customer customer = new Customer();
        try {
            Statement stmt = connection.createStatement();

            String sql = String.format("select id, name, rented_car_id from customer where id = %d", customerId);

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setRentedCarId(rs.getInt("rented_car_id"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return customer;
    }

    public void createCustomer(String name) {
        try {
            Statement stmt = connection.createStatement();

            String sql = String.format("insert into customer (name) values ('%s')", name);
            stmt.execute(sql);
            stmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Car getCar(int carId) {
        Car car = new Car();
        try {
            Statement stmt = connection.createStatement();

            String sql = String.format("select id, name, company_id from car where id = %d", carId);

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                car.setId(rs.getInt("id"));
                car.setName(rs.getString("name"));
                car.setCompanyId(rs.getInt("company_id"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return car;
    }

    public void updateCarForCustomer(int customerId, Integer carId) {
        try {
            Statement stmt = connection.createStatement();

            String sql = String.format("update customer set rented_car_id = %d where id = %d", carId, customerId);

            stmt.execute(sql);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
