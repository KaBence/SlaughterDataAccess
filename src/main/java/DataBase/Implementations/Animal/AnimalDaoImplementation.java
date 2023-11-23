package DataBase.Implementations.Animal;

import DataBase.DAOs.Animal.AnimalDao;
import DataBase.DataBaseConnection;
import Domain.Animal;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
            try (Connection connection = getConnection()) {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO Animal(weight,dateOfDeath,farm,contaminated) VALUES(?,?,?,?)");
                ps.setDouble(1, animal.getWeight());
                ps.setDate(2, convertToSQLDate(animal.getDod()));
                ps.setInt(3, animal.getFarm());
                ps.setBoolean(4,false);
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
                String dod = rs.getString("dateOfDeath");
                int farm = rs.getInt("farm");
                boolean contaminated= rs.getBoolean("contaminated");
                list.add(new Animal(id, weight, dod, farm,contaminated));
            }
            return list;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public ArrayList<Animal> getContaminatedAnimals() {
        ArrayList<Animal> list = new ArrayList<>();
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM Animal where contaminated=true");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("anm_id");
                double weight = rs.getDouble("weight");
                String dod = rs.getString("dateOfDeath");
                int farm = rs.getInt("farm");
                boolean contaminated= rs.getBoolean("contaminated");
                list.add(new Animal(id, weight, dod, farm,contaminated));
            }
            return list;
        } catch (SQLException e) {
            return null;
        }
    }

    private java.sql.Date convertToSQLDate(String date)
    {
        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date date1 = sdf1.parse(date);
            return new java.sql.Date(date1.getTime());
        }
        catch (ParseException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
