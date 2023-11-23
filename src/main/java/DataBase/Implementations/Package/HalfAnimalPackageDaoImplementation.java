package DataBase.Implementations.Package;

import DataBase.DAOs.Package.HalfAnimalPackageDAo;
import DataBase.DataBaseConnection;
import Domain.HalfAnimalPackage;

import java.sql.*;
import java.util.ArrayList;

public class HalfAnimalPackageDaoImplementation implements HalfAnimalPackageDAo {
    public HalfAnimalPackageDaoImplementation() {
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
    public String saveHalfAnimalPackage(HalfAnimalPackage halfAnimalPackage) {
        if (halfAnimalPackage == null) {
            return DataBaseConnection.MANDATORY;
        } else {
            try (Connection connection = getConnection()) {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO halfananimalpackage(package_id) VALUES(?)");
                ps.setInt(1, halfAnimalPackage.getHalf_package_id());
                ps.executeUpdate();
                return DataBaseConnection.SUCCESS;
            } catch (SQLException e) {
                return DataBaseConnection.ERROR;
            }
        }
    }

    @Override
    public ArrayList<HalfAnimalPackage> getHalfAnimalPackages() {
        ArrayList<HalfAnimalPackage> list = new ArrayList<>();
        try (Connection connection = getConnection())
        {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM halfananimalpackage");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("package_id");
                list.add(
                        new HalfAnimalPackage(id));
            }
            return list;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public HalfAnimalPackage getHalfAnimalPackage(int id) {
        ArrayList<HalfAnimalPackage> list = getHalfAnimalPackages();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getHalf_package_id() == id) {
                return list.get(i);
            }
        }
        return null;
    }
    //put animalPartIntoPackage
    public String putAnimalPartIntoHalfAnAnimalPackage(int trayId,int animalpartId, int packageId){
        if (packageId==0){
            return  DataBaseConnection.MANDATORY;
        }
        if(animalpartId==0){
            return DataBaseConnection.MANDATORY;
        }
        else{   try(Connection connection= getConnection()) {
            PreparedStatement ps= connection.prepareStatement("UPDATE AnimalPart SET HalfAnAnimalPackage_id=? WHERE p_id=?");
            ps.setInt(1, packageId);
            ps.setInt(2, animalpartId);
            ps.executeUpdate();
            return DataBaseConnection.SUCCESS;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        }
    }
}
