package carsharing;

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

//            System.out.println("Connectin to the db...");
            connection = DriverManager.getConnection(dbUrl);
            connection.setAutoCommit(true);
//            System.out.println("Connected successfully");

            Statement stmt = connection.createStatement();
            String sql = "DROP TABLE IF EXISTS COMPANY";
            stmt.execute(sql);
//            System.out.println("Table dropped if exists");


            sql = "CREATE TABLE IF NOT EXISTS company (id identity unique, name varchar unique not null)";
            stmt.execute(sql);
//            System.out.println("New table created");

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

}
