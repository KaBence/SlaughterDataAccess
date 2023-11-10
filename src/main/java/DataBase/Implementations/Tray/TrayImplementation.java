package DataBase.Implementations.Tray;

import DataBase.DAOs.Tray.TrayDao;
import DataBase.DataBaseConnection;
import Domain.AnimalPart;
import Domain.Tray;

import java.sql.*;
import java.util.ArrayList;

public class TrayImplementation  implements TrayDao {

    public TrayImplementation(){
        try{
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }
    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=slaughterhouse",
                "postgres", "password");
    }
    @Override
    public Tray getTray(int id) {
        ArrayList<Tray> trays= new ArrayList<>();
        try(Connection connection= getConnection()){
            PreparedStatement ps= connection.prepareStatement("SELECT * FROM TRAY");
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                int trayId = rs.getInt("tray_id");
                double maxWeight= rs.getDouble("maxWeight");
                int oneKindPackageId= rs.getInt("OneKindPackege_id");
                int HalfAnAnimalPackage= rs.getInt("HalfAnAnimalPackage_id");
                trays.add(new Tray(trayId, maxWeight,oneKindPackageId, HalfAnAnimalPackage));
            }
            return trays.get(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    @Override
    public String putIntoTray(AnimalPart animalPart, int trayId) {
        if(animalPart== null){
            return DataBaseConnection.MANDATORY;
        }
        if(trayId==0 ){
            return DataBaseConnection.MANDATORY;
        }
        else{
            try(Connection connection= getConnection()) {
           int id=getTray(trayId).getId();
                PreparedStatement ps= connection.prepareStatement("UPDATE AnimalPart SET tray_id=? WHERE p_id=?");
                ps.setInt(1, id);
                ps.setInt(2,animalPart.getId());
                ps.executeUpdate();
                return DataBaseConnection.SUCCESS;
            } catch (SQLException e) {
                return DataBaseConnection.ERROR;
            }
        }
    }

    @Override
    public String takeFromTray(int animalpartId, int packageId) {
        if(packageId==0){
            return  DataBaseConnection.MANDATORY;
        }
        if(animalpartId==0){
            return DataBaseConnection.MANDATORY;
        }
        else{
            try(Connection connection=getConnection()) {
                if(){
                PreparedStatement preparedStatement= connection.prepareStatement("UPDATE")}
                else{

                }

                PreparedStatement ps= connection.prepareStatement("UPDATE AnimalPart SET tray_id=? WHERE p_id=? ");
                ps.setInt(1,0);
                ps.setInt(2,animalpartId);

            } catch (SQLException e) {
                return DataBaseConnection.ERROR;
            }
        }
    }

    @Override
    public ArrayList<Tray> getAllTrays() {
        ArrayList<Tray> trays = new ArrayList<>();
        try(Connection connection= getConnection()){
            PreparedStatement ps= connection.prepareStatement("SELECT * FROM TRAY");
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                int trayId = rs.getInt("tray_id");
                double maxWeight= rs.getDouble("maxWeight");
                int oneKindPackageId= rs.getInt("OneKindPackege_id");
                int HalfAnAnimalPackage= rs.getInt("HalfAnAnimalPackage_id");
                trays.add(new Tray(trayId, maxWeight,oneKindPackageId, HalfAnAnimalPackage));
            }
            return trays;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
