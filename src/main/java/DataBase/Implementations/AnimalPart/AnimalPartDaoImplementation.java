package DataBase.Implementations.AnimalPart;

import DataBase.DAOs.AnimalPart.AnimalPartDao;
import DataBase.DataBaseConnection;
import Domain.Animal;
import Domain.AnimalPart;

import java.sql.*;
import java.util.ArrayList;

public class AnimalPartDaoImplementation implements AnimalPartDao {


    public AnimalPartDaoImplementation() {
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
    public String saveAnimalPart(AnimalPart animalPart) {
        if (animalPart == null) {
            return DataBaseConnection.MANDATORY;
        } else {
            try (Connection connection = getConnection()) {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO AnimalPart(anm_pt_name,weight, anm_id) VALUES(?,?,?)");
                ps.setString(1, animalPart.getName());
                ps.setDouble(2, animalPart.getWeight());
                ps.setInt(3, animalPart.getAnimalId());
                ps.executeUpdate();
                return DataBaseConnection.SUCCESS;
            } catch (SQLException e) {
                return DataBaseConnection.ERROR;
            }
        }
    }

    @Override
    public ArrayList<AnimalPart> getAllAnimalParts() {
        ArrayList<AnimalPart> list = new ArrayList<>();
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM AnimalPart");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("p_id");
                String name = rs.getString("anm_pt_name");
                double weight = rs.getDouble("weight");
                int anId = rs.getInt("anm_id");
                list.add(
                        new AnimalPart(id, name, weight, anId));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
