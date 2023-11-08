package DataBase.Implementations.AnimalPart;

import DataBase.DAOs.AnimalPart.AnimalPartDao;
import DataBase.DataBaseConnection;
import Domain.AnimalPart;

import java.util.ArrayList;

public class AnimalPartDaoImplementation implements AnimalPartDao {

    /*
    public AnimalPartDaoImplementation(){
        try{
            DriverManager.registerDriver(new org.postgresql.Driver());
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }
    private Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres?currentSchema=distributionsystem",
                "postgres", "password");
    }

     */
    @Override
    public String saveAnimalPart(AnimalPart animalPart) {
        return DataBaseConnection.ALREADY;
    }

    @Override
    public ArrayList<AnimalPart> getAllAnimalParts() {
        return null;
    }
}
