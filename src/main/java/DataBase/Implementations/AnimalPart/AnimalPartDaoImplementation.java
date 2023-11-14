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
                PreparedStatement ps = connection.prepareStatement("INSERT INTO AnimalPart(anm_pt_name,weight, anm_id,contaminated) VALUES(?,?,?,?)");
                ps.setString(1, animalPart.getName());
                ps.setDouble(2, animalPart.getWeight());
                ps.setInt(3, animalPart.getAnimalId());
                ps.setBoolean(4,false);
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
                boolean contaminated=rs.getBoolean("contaminated");
                list.add(new AnimalPart(id, name, weight, anId,contaminated));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public String recallAnimal(int id) {
        try (Connection connection=getConnection()){
            PreparedStatement ps=connection.prepareStatement("Update animalpart set contaminated=true where anm_id=?");
            ps.setInt(1,id);
            ps.executeUpdate();

            PreparedStatement ps1=connection.prepareStatement("Update animal set contaminated=true where anm_id=?");
            ps1.setInt(1,id);
            ps1.executeUpdate();
            return DataBaseConnection.SUCCESS;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return DataBaseConnection.ERROR;
        }
    }
}
