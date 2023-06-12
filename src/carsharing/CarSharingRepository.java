package carsharing;

import carsharing.entities.Car;
import carsharing.entities.Company;

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
            String sql = "DROP TABLE IF EXISTS car";
            stmt.execute(sql);
            sql = "DROP TABLE IF EXISTS COMPANY";
            stmt.execute(sql);
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

        Company company = new Company(0, "unknown");
        try {
            Statement stmt = connection.createStatement();

            String sql = String.format("select id, name from company where id = '%d'", id);

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
            String sql = String.format("select id, name from car where company_id = %d", companyId);
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                cars.add(new Car(rs.getInt("id"), rs.getString("name")));
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

}
