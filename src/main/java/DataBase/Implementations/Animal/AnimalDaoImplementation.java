package DataBase.Implementations.Animal;

import DataBase.DAOs.Animal.AnimalDao;
import DataBase.DataBaseConnection;
import Domain.Animal;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class AnimalDaoImplementation implements AnimalDao {


    public AnimalDaoImplementation() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres?currentSchema=slaughterhouse",
                "postgres", "password");
    }


    @Override
    public String saveAnimal(Animal animal) {
        if (animal == null) {
            return DataBaseConnection.MANDATORY;
        } else {
            java.sql.Date dod = new java.sql.Date(animal.getDod().getDay(), animal.getDod().getMonth(), animal.getDod().getYear());
            try (Connection connection = getConnection()) {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO Animal(weight,dateOfDeath,farm) VALUES(?,?,?)");
                ps.setDouble(1, animal.getWeight());
                ps.setDate(2, dod);
                ps.setInt(3, animal.getId());
                ps.executeUpdate();
                return DataBaseConnection.SUCCESS;
            } catch (SQLException e) {
                return DataBaseConnection.ERROR;
            }
        }
    }

    @Override
    public Animal getAnimal(int id) {
        ArrayList<Animal> list = getAllAnimals();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                return list.get(i);
            }
        }
        return null;
    }

    @Override
    public ArrayList<Animal> getAllAnimals() {
        ArrayList<Animal> list = new ArrayList<>();
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM Animal");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("anm_id");
                double weight = rs.getDouble("weight");
                java.sql.Date x = rs.getDate("dateOfDeath");
                int farm = rs.getInt("farm");
                list.add(
                        new Animal(id, weight, new Date(x.getDay(), x.getMonth(), x.getYear()), farm));
            }
            return list;
        } catch (SQLException e) {
            return null;
        }
    }
}
