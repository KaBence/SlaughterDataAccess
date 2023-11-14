package DataBase.Implementations.Package;

import DataBase.DAOs.Package.OneKindAnimalPackageDao;
import DataBase.DataBaseConnection;
import Domain.HalfAnimalPackage;
import Domain.OneKindAnimalPackage;

import java.sql.*;
import java.util.ArrayList;

public class OneKindAnimalPackageDaoImplementation implements OneKindAnimalPackageDao {

    public OneKindAnimalPackageDaoImplementation() {
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
    public String saveOneKindAnimalPackage(OneKindAnimalPackage oneKindAnimalPackage) {
        if (oneKindAnimalPackage == null) {
            return DataBaseConnection.MANDATORY;
        } else {
            try (Connection connection = getConnection()){
                PreparedStatement ps = connection.prepareStatement("INSERT INTO onekindpackage(parttype, package_id) VALUES(?,?)");
                ps.setString(1, oneKindAnimalPackage.getPartType());
                ps.setInt(2, oneKindAnimalPackage.getOne_package_id());
                ps.executeUpdate();
                return DataBaseConnection.SUCCESS;
            }
            catch (SQLException e)
            {
                return DataBaseConnection.ERROR;
            }
        }
    }

    @Override
    public ArrayList<OneKindAnimalPackage> getOneKindAnimalPackages() {
        ArrayList<OneKindAnimalPackage> list = new ArrayList<>();
        try (Connection connection = getConnection())
        {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM onekindpackage");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("package_id");
                String partType = rs.getString("parttype");
                list.add(
                        new OneKindAnimalPackage(id,partType));
            }
            return list;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public OneKindAnimalPackage getOneKindAnimalPackage(int id) {
        ArrayList<OneKindAnimalPackage> list = getOneKindAnimalPackages();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getOne_package_id() == id) {
                return list.get(i);
            }
        }
        return null;
    }
}
