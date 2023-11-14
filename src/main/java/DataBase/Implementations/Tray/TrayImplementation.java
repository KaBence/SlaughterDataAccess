package DataBase.Implementations.Tray;

import DataBase.DAOs.Tray.TrayDao;
import DataBase.DataBaseConnection;
import Domain.Animal;
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
        ArrayList<Tray> list = getAllTrays();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                return list.get(i);
            }
        }
        return null;
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
           //here goes method for getttig all AnimalParts from the same tray and chcecking the name of said animal part
              ArrayList<AnimalPart> currentParts= getAllAnimalPartsFromTheTray(trayId);
              if(currentParts.get(currentParts.size()-1).getName().equals(animalPart.getName())){
                  PreparedStatement ps= connection.prepareStatement("UPDATE AnimalPart SET tray_id=? WHERE p_id=?");

                  ps.setInt(1, id);
                  ps.setInt(2,animalPart.getId());
                  ps.executeUpdate();
                  return DataBaseConnection.SUCCESS;
              }
              else{
                  return DataBaseConnection.ERROR;
              }


            } catch (SQLException e) {
                return DataBaseConnection.ERROR;
            }
        }
    }

    @Override
    public String takeFromTray(int trayId,int animalpartId, int packageId) {
        if(packageId==0){
            return  DataBaseConnection.MANDATORY;
        }
        if(animalpartId==0){
            return DataBaseConnection.MANDATORY;
        }
        else{
            try(Connection connection=getConnection()) {
                ArrayList<AnimalPart> currentParts= getAllAnimalPartsFromTheTray(trayId);

//this removes the trayId from certain AnimalPart -takes it away from tray
                PreparedStatement ps= connection.prepareStatement("UPDATE AnimalPart SET tray_id=? WHERE p_id=? ");
                ps.setInt(1,0);
                ps.setInt(2,animalpartId);
                ps.executeUpdate();
                return DataBaseConnection.SUCCESS;

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
                trays.add(new Tray(trayId, maxWeight));
            }
            return trays;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public ArrayList<AnimalPart> getAllAnimalPartsFromTheTray(int id) {
        ArrayList<AnimalPart> animalPartsFromTheSameTray = new ArrayList<>();
        try(Connection connection=getConnection()){
            PreparedStatement ps= connection.prepareStatement("SELECT * FROM AnimalPart WHERE tray_id=?");
            ps.setInt(1,id);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                int itemId= rs.getInt("p_id");
                String itemName= rs.getString("anm_pt_name");
                double weight= rs.getDouble("weight");
                int animalId=rs.getInt("anm_id");
                int trayId=rs.getInt("tray_id");
                int oneKinfOfPackageId= rs.getInt("OneKindPackege_id");
                int halfAnAnimalPackageId= rs.getInt("HalfAnAnimalPackage_id");

                animalPartsFromTheSameTray.add(new AnimalPart(itemId,itemName,weight,animalId,trayId, oneKinfOfPackageId, halfAnAnimalPackageId));
            }
            return animalPartsFromTheSameTray;
        } catch (SQLException e) {
            return null;
        }
    }
}
