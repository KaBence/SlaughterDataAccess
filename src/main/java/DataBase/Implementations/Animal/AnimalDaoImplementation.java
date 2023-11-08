package DataBase.Implementations.Animal;

import DataBase.DAOs.Animal.AnimalDao;
import DataBase.DataBaseConnection;
import Domain.Animal;
import slaughter.DTOAnimal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class AnimalDaoImplementation implements AnimalDao {


    public AnimalDaoImplementation(){
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


    @Override
    public String saveAnimal(Animal animal)
    {
        if (animal == null)
        {
            return DataBaseConnection.MANDATORY;
        }
        else
        {

        }
        return DataBaseConnection.ALREADY;
    }

    @Override
    public Animal getAnimal(int id) {
        return null;
    }

    @Override
    public ArrayList<Animal> getAllAnimals() {
        return null;
    }
}
